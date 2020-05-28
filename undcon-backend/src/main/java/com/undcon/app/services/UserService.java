package com.undcon.app.services;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.EmployeeEntity;
import com.undcon.app.model.UserEntity;
import com.undcon.app.multitenancy.ThreadLocalStorage;
import com.undcon.app.repositories.IUserRepository;
import com.undcon.app.utils.NumberUtils;

@Component
public class UserService extends AbstractService<UserEntity>{

	public static String SHARED_KEY = "a0a2abd8-6162-41c3-83d6-1cf559b46afc";

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private EmployeeService employeeService;
	
	public Page<UserEntity> getAll(String filter, Integer page, Integer size) throws UndconException {
		permissionService.checkPermission(getResourceType());
		return super.getAll(UserEntity.class, filter, page, size);
	}

	@Override
	protected void validateBeforePost(UserEntity user) throws UndconException {
		super.validateBeforePost(user);
		if (NumberUtils.longIsPositiveValue(user.getId())) {
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
		
		try {
			user.setPassword(criptyPassword(user.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void validateBeforeUpdate(UserEntity user) throws UndconException {
		super.validateBeforeUpdate(user);
		validateLoginFormat(user.getLogin());
		UserEntity find = findById(user.getId());
		
		//Validar se usuário tem permissão para alterar ResetPassword
		if (find.isResetPassword()) {
			try {
				user.setPassword(criptyPassword(user.getPassword()));
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
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

	@Override
	protected JpaRepository<UserEntity, Long> getRepository() {
		return userRepository;
	}

	@Override
	protected ResourceType getResourceType() {
		return ResourceType.USER;
	}

}
