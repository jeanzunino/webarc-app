package com.undcon.app.services;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

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
import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.LoginException;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.mappers.UserMapper;
import com.undcon.app.model.UserEntity;
import com.undcon.app.multitenancy.DataSourceProperties;
import com.undcon.app.multitenancy.ThreadLocalStorage;
import com.undcon.app.repositories.IUserRepository;

import net.minidev.json.JSONObject;

@Component
public class LoginService {

	public static String SHARED_KEY = "a0a2abd8-6162-41c3-83d6-1cf559b46afc";

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private DataSourceProperties dataSourceProperties;

	public LoginResponseDto login(LoginRequestDto dto)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, UndconException {

		String tenantByLogin = getTenantByLoginAndDomain(dto.getLogin());
		String login = getLoginByLoginAndDomain(dto.getLogin());
		if (!dataSourceProperties.getDatasources().containsKey(tenantByLogin)) {
			throw new LoginException(UndconError.INVALID_USER_OR_PASSWORD);
		}
		ThreadLocalStorage.setTenantName(tenantByLogin);

		String pass = userService.criptyPassword(dto.getPassword());
		UserEntity user = userRepository.findAllByLoginAndPassword(login, pass);
		boolean resetPassword = false;
		if (user == null) {
			throw new LoginException(UndconError.INVALID_USER_OR_PASSWORD);
		}
		
		if (user.getTokenResetarSenha() != null) {
			throw new LoginException(UndconError.INVALID_USER_RESET_PASSWORD);
		}

		if (!user.isActive()) {
			throw new LoginException(UndconError.USER_BLOCKED);
		}

		if (user.isResetPassword()) {
			resetPassword = true;
		}
		
		// Create payload
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userId", user.getId());
		jsonObject.put("login", login);
		jsonObject.put("name", user.getEmployee().getName());
		jsonObject.put("resetPassword", resetPassword);
		jsonObject.put("tenant", tenantByLogin);
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

		UserDto userDto = userMapper.toDto(user);
		List<ResourceType> resources = permissionService.getResourcesOfUser(user);
		LoginResponseDto response = new LoginResponseDto(ThreadLocalStorage.getTenantName(), token, resetPassword,
				userDto, resources);
		return response;
	}

	private static String getTenantByLoginAndDomain(String loginAndDomain) throws UndconException {
		String[] split = loginAndDomain.trim().split("@");
		if (split.length != 2) {
			throw new LoginException(UndconError.INVALID_LOGIN_FORMAT_WITH_DOMAIN);
		}
		String tenant = split[1];
		return tenant;
	}
	
	public static String getLoginByLoginAndDomain(String loginAndDomain) throws UndconException {
		String[] split = loginAndDomain.trim().split("@");
		if (split.length != 2) {
			throw new UndconException(UndconError.INVALID_LOGIN_FORMAT_WITH_DOMAIN);
		}
		return split[0];
	}

}
