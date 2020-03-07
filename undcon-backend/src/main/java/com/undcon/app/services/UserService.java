package com.undcon.app.services;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.undcon.app.enums.ResourseType;
import com.undcon.app.model.UserEntity;
import com.undcon.app.repositories.IUserRepository;
import com.undcon.app.utils.LongUtils;
import com.undcon.app.utils.PageUtils;

@Component
public class UserService {

	public static String SHARED_KEY = "a0a2abd8-6162-41c3-83d6-1cf559b46afc";
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
    private PermissionService permissionService;
	
	public List<UserEntity> getAll(Integer page, Integer size) {
        return userRepository.findAll(PageUtils.createPageRequest(page, size)).getContent();
    }
	
	public UserEntity findById(Long id) {
        return userRepository.findOne(id);
    }
	
	public UserEntity persist(UserEntity user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		permissionService.checkPermission(ResourseType.USER);
	    if(LongUtils.longIsPositiveValue(user.getId())) {
	        throw new IllegalArgumentException("O novo registro a ser salvo não pode ter o id preenchido.");
	    }
	    user.setPassword(criptyPassword(user.getPassword()));
        return userRepository.save(user);
    }
    
    public UserEntity update(UserEntity user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	permissionService.checkPermission(ResourseType.USER);
        UserEntity find = findById(user.getId());
        find.setLogin(user.getLogin());
        find.setEmployee(user.getEmployee());
        if (find.getPassword() == null || find.getPassword().isEmpty()) {
            find.setPassword(criptyPassword(user.getPassword()));
        }
        return userRepository.save(find);
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

    public void delete(long id) {
    	permissionService.checkPermission(ResourseType.USER);
        userRepository.delete(id);
    }

    @Transactional
    public void resetPassword(long id) throws IllegalAccessException {
        permissionService.checkPermission(ResourseType.CONFIGURATION);
        UserEntity find = findById(id);
        find.setPassword("");
        userRepository.save(find);
    }

    
	
	
}
