package com.undcon.app.rest.filters;

import java.io.IOException;
import java.text.ParseException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACVerifier;
import com.undcon.app.config.multitenancy.ThreadLocalStorage;
import com.undcon.app.services.LoginService;

import net.minidev.json.JSONObject;

@Provider
public class RequestFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext ctx) throws IOException {

		if (ctx.getMethod().equals("OPTIONS")) {
			return;
		}
		MultivaluedMap<String, String> headers = ctx.getHeaders();

		if (headers == null) {
			return;
		}

		if (!headers.containsKey("Authorization")) {
			return;
		}

		String token = headers.getFirst("Authorization");

		if (token == null && !ctx.getUriInfo().getAbsolutePath().getPath().contains("login")) {
			throw new WebApplicationException("Token não informado", Response.Status.UNAUTHORIZED);
		}
		if (ctx.getUriInfo().getAbsolutePath().getPath().contains("login")) {
			return;
		}

		String tenant = verifyTokenAndGetTenant(token);

		ThreadLocalStorage.setTenantName(tenant);
	}

	private String verifyTokenAndGetTenant(String token) {
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

		return (String) jsonObject.get("tenant");
	}
}
