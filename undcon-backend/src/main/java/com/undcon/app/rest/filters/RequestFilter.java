package com.undcon.app.rest.filters;

import java.io.IOException;
import java.text.ParseException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.springframework.beans.factory.annotation.Autowired;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACVerifier;
import com.undcon.app.enums.UndconError;
import com.undcon.app.model.UserEntity;
import com.undcon.app.multitenancy.ThreadLocalStorage;
import com.undcon.app.rest.models.ErrorMessageModel;
import com.undcon.app.services.LoginService;
import com.undcon.app.services.UserService;

import net.minidev.json.JSONObject;

@Provider
public class RequestFilter implements ContainerRequestFilter {

	@Autowired
	private UserService userService;
	
    @Override
    public void filter(ContainerRequestContext ctx) throws IOException {

        if (ctx.getMethod().equals("OPTIONS")) {
            return;
        }
        MultivaluedMap<String, String> headers = ctx.getHeaders();

        if (headers == null) {
            return;
        }

        if (ctx.getUriInfo().getAbsolutePath().getPath().contains("login")) {
            return;
        }
        
        if (!headers.containsKey("Authorization")) {
        	throw new WebApplicationException("Token não informado", Response.Status.UNAUTHORIZED);
        }

        String token = headers.getFirst("Authorization");

        if (token == null && !ctx.getUriInfo().getAbsolutePath().getPath().contains("login")) {
            throw new WebApplicationException("Token não informado", Response.Status.UNAUTHORIZED);
        }

        JSONObject payloadAsJsonObject = verifyTokenAndGetTenant(token);

        ThreadLocalStorage.setTenantName((String) payloadAsJsonObject.get("tenant"));
        Long userId = (Long) payloadAsJsonObject.get("userId");
        
        UserEntity user = userService.findById(userId);
        if(user == null || !user.isActive()) {
        	throw new WebApplicationException(Response
				     .status(Response.Status.UNAUTHORIZED)
				     .entity(new ErrorMessageModel(UndconError.INVALID_USER_LOGGED)).build());
        }

        ThreadLocalStorage.setUser(user);
        
        Boolean resetPassword = (Boolean) payloadAsJsonObject.get("resetPassword");
        if(resetPassword == null) {
        	throw new WebApplicationException(Response
				     .status(Response.Status.UNAUTHORIZED)
				     .entity(new ErrorMessageModel(UndconError.INVALID_TOKEN_RETRY_LOGIN)).build());
        }
        if(resetPassword && !ctx.getUriInfo().getAbsolutePath().getPath().contains("user")) {
        	throw new WebApplicationException(Response
				     .status(Response.Status.UNAUTHORIZED)
				     .entity(new ErrorMessageModel(UndconError.INVALID_TOKEN_UPDATE_PASSWORD)).build());
        }
    }

    private JSONObject verifyTokenAndGetTenant(String token) {
        JWSObject jwsObject;
        try {
            jwsObject = JWSObject.parse(token);
        } catch (ParseException e) {
            throw new RuntimeException("Erro parsear o Token", e);
        }

        JWSVerifier verifier = null;
        try {
            verifier = new MACVerifier(LoginService.SHARED_KEY.getBytes());
        } catch (JOSEException e1) {
            throw new RuntimeException("Erro ao verificar o Token", e1);
        }

        boolean verifiedSignature = false;

        try {
            verifiedSignature = jwsObject.verify(verifier);
        } catch (JOSEException e) {
            throw new RuntimeException("Erro ao verificar o Token", e);
        }

        if (!verifiedSignature) {
            System.out.println("Bad JWS signature!");
            throw new WebApplicationException("Token inválido", Response.Status.UNAUTHORIZED);
        }

        Payload payload = jwsObject.getPayload();
        JSONObject jsonObject = payload.toJSONObject();

        return jsonObject;
    }
}
