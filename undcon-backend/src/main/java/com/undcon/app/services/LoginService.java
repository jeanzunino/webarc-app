package com.undcon.app.services;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.undcon.app.dtos.LoginRequestDto;
import com.undcon.app.dtos.LoginResponseDto;
import com.undcon.app.dtos.UserDto;
import com.undcon.app.model.UserEntity;
import com.undcon.app.multitenancy.ThreadLocalStorage;
import com.undcon.app.repositories.IUserRepository;

import net.minidev.json.JSONObject;

@Component
public class LoginService {

	public static String SHARED_KEY = "a0a2abd8-6162-41c3-83d6-1cf559b46afc";

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private UserService userService;

	public LoginResponseDto login(LoginRequestDto dto)
			throws LoginException, NoSuchAlgorithmException, UnsupportedEncodingException {

		String tenantByLogin = getTenantByLogin(dto.getLogin());
		ThreadLocalStorage.setTenantName(tenantByLogin);

		String pass = userService.criptyPassword(dto.getPassword());
		UserEntity user = userRepository.findAllByLoginAndPassword(dto.getLogin(), pass);
		if (user == null) {
			throw new LoginException("Usuário/Senha inválido");
		}

		// Create payload
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", user.getId());
		jsonObject.put("login", user.getLogin());
		jsonObject.put("tenant", getTenantByLogin(user.getLogin()));
		Payload payload = new Payload(jsonObject);

		System.out.println("JWS payload message: " + user);

		// Create JWS header with HS256 algorithm
		JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

		System.out.println("JWS header: " + header.toJSONObject());

		// Create JWS object
		JWSObject jwsObject = new JWSObject(header, payload);

		// Create HMAC signer

		JWSSigner signer = null;
		try {
			signer = new MACSigner(SHARED_KEY.getBytes());
		} catch (KeyLengthException e1) {
			e1.printStackTrace();
		}

		try {
			jwsObject.sign(signer);
		} catch (JOSEException e) {

			System.err.println("Couldn't sign JWS object: " + e.getMessage());
			throw new RuntimeException("Erro ao gerar o Token", e);
		}

		// Serialise JWS object to compact format
		String token = jwsObject.serialize();

		System.out.println("Serialised JWS object: " + token);

		UserDto userDto = new UserDto(user.getLogin(), user.getEmployee().getName());
		LoginResponseDto response = new LoginResponseDto(ThreadLocalStorage.getTenantName(), token, userDto);

		return response;
	}

	private static String getTenantByLogin(String login) throws LoginException {
		String[] split = login.trim().split("@");
		if (split.length != 2) {
			throw new LoginException("Usuário no fomato inválido");
		}
		String tenant = split[1];
		return tenant;
	}

}
