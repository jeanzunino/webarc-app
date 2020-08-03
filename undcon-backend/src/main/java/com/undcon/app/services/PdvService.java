package com.undcon.app.services;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.undcon.app.dtos.OpenPdvDto;
import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.LoginException;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.PdvEntity;
import com.undcon.app.model.UserEntity;
import com.undcon.app.repositories.IPdvRepository;
import com.undcon.app.repositories.IUserRepository;
import com.undcon.app.utils.PageUtils;

@Component
public class PdvService {

	@Autowired
	private IPdvRepository repository;

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private UserService userService;

	@Autowired
	private IUserRepository userRepository;

	public Page<PdvEntity> getAll(String name, Integer page, Integer size) {
		return repository.findAll(PageUtils.createPageRequest(page, size));
	}

	public PdvEntity findById(Long id) {
		return repository.findOne(id);
	}

	public PdvEntity persist(OpenPdvDto dto) throws UndconException {
		permissionService.checkPermission(ResourceType.PROVIDER);

		String login = LoginService.getLoginByLoginAndDomain(dto.getLogin());
		String pass;
		try {
			pass = userService.criptyPassword(dto.getPassword());
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new UndconException(UndconError.GENERIC_ERROR);
		}
		UserEntity responsibleUserInOpening = userRepository.findAllByLoginAndPassword(login, pass);
		if (responsibleUserInOpening == null) {
			throw new UndconException(UndconError.INVALID_USER_OR_PASSWORD);
		}
		if (!responsibleUserInOpening.isActive()) {
			throw new UndconException(UndconError.USER_BLOCKED);
		}

		UserEntity user = userService.getCurrentUser();
		
		List<PdvEntity> findByUser = repository.findByUser(user);
		if(!findByUser.isEmpty()) {
			throw new UndconException(UndconError.PDV_ALREADY_OPEN_TO_THE_LOGGED_USER);
		}
		Date openingDate = new Date(System.currentTimeMillis());

		PdvEntity entity = new PdvEntity(null, user, responsibleUserInOpening, null, openingDate, null,
				Double.parseDouble(dto.getPdvValue()), null);
		return repository.save(entity);
	}

	public PdvEntity update(OpenPdvDto dto) throws UndconException {
		permissionService.checkPermission(ResourceType.PROVIDER);

		String login = LoginService.getLoginByLoginAndDomain(dto.getLogin());
		UserEntity responsibleUserInClosing = userRepository.findAllByLoginAndPassword(login, dto.getPassword());
		if (!responsibleUserInClosing.isActive()) {
			throw new LoginException(UndconError.USER_BLOCKED);
		}

		PdvEntity findOne = repository.findOne(dto.getId());
		findOne.setResponsibleUserInClosing(responsibleUserInClosing);

		Date closingDate = new Date(System.currentTimeMillis());
		findOne.setClosingDate(closingDate);

		findOne.setClosingValue(Double.parseDouble(dto.getPdvValue()));
		return repository.save(findOne);
	}

	public void delete(long id) throws UndconException {
		permissionService.checkPermission(ResourceType.PROVIDER);
		repository.delete(id);
	}
}
