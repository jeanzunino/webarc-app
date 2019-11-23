package com.undcon.app.services;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
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
public class UserService {

	public static String SHARED_KEY = "a0a2abd8-6162-41c3-83d6-1cf559b46afc";
	
	@Autowired
	private IUserRepository userRepository;
	
	public UserEntity save(UserEntity user) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		user.setPassword(criptyPassword(user.getPassword()));
		return userRepository.save(user);
	}
	
	public String criptyPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		if(password == null){
			return null;
		}
		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = algorithm.digest(password.getBytes("UTF-8"));
        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
          hexString.append(String.format("%02X", 0xFF & b));
        }
        return hexString.toString(); 
	}
	
	
}
