package com.undcon.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.undcon.app.enums.ResourseType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.ProviderEntity;
import com.undcon.app.repositories.IProviderRepository;
import com.undcon.app.utils.PageUtils;

@Component
public class ProviderService {

	@Autowired
	private IProviderRepository providerRepository;
	
	@Autowired
	private PermissionService permissionService;

	public List<ProviderEntity> getAll(String name, Integer page, Integer size) {
		if (StringUtils.isEmpty(name)) {
			return providerRepository.findAll(PageUtils.createPageRequest(page, size)).getContent();
		}
		return providerRepository.findAllByName(name, PageUtils.createPageRequest(page, size)).getContent();
    }
	
	public ProviderEntity findById(Long id) {
        return providerRepository.findOne(id);
    }
	
	public ProviderEntity persist(ProviderEntity entity) throws UndconException {
		permissionService.checkPermission(ResourseType.PROVIDER);
		validateName(0L, entity.getName());
		return providerRepository.save(entity);
	}

	public ProviderEntity update(ProviderEntity entity) throws UndconException {
		permissionService.checkPermission(ResourseType.PROVIDER);
		validateName(entity.getId(), entity.getName());
		return providerRepository.save(entity);
	}

	private void validateName(Long id, String name) throws UndconException {
		List<ProviderEntity> findByIdNotAndName = providerRepository.findByIdNotAndName(id, name);
		if(!findByIdNotAndName.isEmpty()) {
			throw new UndconException(UndconError.NAME_ALREADY_EXISTS);
		}
	}

	public void delete(long id) throws UndconException {
		permissionService.checkPermission(ResourseType.PROVIDER);
		providerRepository.delete(id);
	}
}
