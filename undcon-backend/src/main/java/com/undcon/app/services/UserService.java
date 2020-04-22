package com.undcon.app.services;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.EmployeeEntity;
import com.undcon.app.model.UserEntity;
import com.undcon.app.multitenancy.ThreadLocalStorage;
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
	
	@Autowired
	private EmployeeService employeeService;

	public Page<UserEntity> getAll(Integer page, Integer size, String login) throws UndconException {
		permissionService.checkPermission(ResourceType.USER);
		if (login == null) {
			return userRepository.findAll(PageUtils.createPageRequest(page, size));
		}
		return userRepository.findAllByLoginContainingIgnoreCase(login, PageUtils.createPageRequest(page, size));
	}

	public UserEntity findById(Long id) {
		return userRepository.findOne(id);
	}

	public UserEntity persist(UserEntity user)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, UndconException {
		permissionService.checkPermission(ResourceType.USER);
		if (LongUtils.longIsPositiveValue(user.getId())) {
			throw new IllegalArgumentException("O novo registro a ser salvo não pode ter o id preenchido.");
		}
		validateLoginFormat(user.getLogin());
		if(hasUserByLogin(user.getLogin())) {
			throw new UndconException(UndconError.LOGIN_ALREADY_EXISTS);
		}
		
		user.setPermission(permissionService.validateAndGet(user.getPermission().getId()));
		
		user.setEmployee(employeeService.validateAndGet(user.getEmployee().getId()));

		if(hasUserByEmployee(user.getEmployee())) {
			throw new UndconException(UndconError.LOGIN_ALREADY_EXISTS_IN_EMPLOYEE);
		}
		
		//TODO Validar se tem @dominio
		user.setPassword(criptyPassword(user.getPassword()));
		return userRepository.save(user);
	}

	public boolean hasUserByLogin(String login) {
		return userRepository.findByLogin(login).size() > 0;
	}
	
	public void validateLoginFormat(String login) throws UndconException {
		if(login.length() < 3 || login.contains("@")){
			throw new UndconException(UndconError.INVALID_LOGIN_FORMAT);
		}
	}
	
	public boolean hasUserByEmployee(EmployeeEntity employee) {
		return userRepository.findByEmployee(employee).size() > 0;
	}

	public UserEntity update(UserEntity user)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, UndconException {
		permissionService.checkPermission(ResourceType.USER);
		validateLoginFormat(user.getLogin());
		UserEntity find = findById(user.getId());
		
		//Validar se usuário tem permissão para alterar ResetPassword
		find.setResetPassword(user.isResetPassword());
		find.setLogin(user.getLogin());
		find.setEmployee(user.getEmployee());
		if (find.isResetPassword()) {
			find.setPassword(criptyPassword(user.getPassword()));
		}
		
		return userRepository.save(find);
	}

	public String criptyPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		if (password == null) {
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

	public void delete(long id) throws UndconException {
		permissionService.checkPermission(ResourceType.USER);
		userRepository.delete(id);
	}

	@Transactional
	public void resetPassword(long id) throws UndconException {
		permissionService.checkPermission(ResourceType.CONFIGURATION);
		UserEntity find = findById(id);
		find.setResetPassword(true);
		userRepository.save(find);
	}

	public UserEntity getCurrentUser() {
		return ThreadLocalStorage.getUser();
	}

	public List<ResourceType> getPermissionOfLoggeduser() {
		return permissionService.getResourcesOfUser(getCurrentUser());
	}

}
