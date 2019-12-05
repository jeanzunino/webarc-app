package com.undcon.app.services;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.undcon.app.model.UserEntity;
import com.undcon.app.repositories.IUserRepository;

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
