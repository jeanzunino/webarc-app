package com.undcon.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.ServiceTypeEntity;
import com.undcon.app.repositories.IServiceTypeRepository;
import com.undcon.app.utils.PageUtils;

@Component
public class ServiceTypeService {

	@Autowired
	private IServiceTypeRepository repository;
	
	@Autowired
	private PermissionService permissionService;

	public Page<ServiceTypeEntity> getAll(Integer page, Integer size) {
        return repository.findAll(PageUtils.createPageRequest(page, size));
    }
	
	public ServiceTypeEntity findById(Long id) {
        return repository.findOne(id);
    }
	
	public ServiceTypeEntity persist(ServiceTypeEntity entity) throws UndconException {
		permissionService.checkPermission(ResourceType.SERVICE_TYPE);
		validateName(0L, entity.getName());
		return repository.save(entity);
	}

	public ServiceTypeEntity update(ServiceTypeEntity entity) throws UndconException {
		permissionService.checkPermission(ResourceType.SERVICE_TYPE);
		validateName(entity.getId(), entity.getName());
		return repository.save(entity);
	}

	private void validateName(Long id, String name) throws UndconException {
		List<ServiceTypeEntity> findByIdNotAndName = repository.findByIdNotAndName(id, name);
		if(!findByIdNotAndName.isEmpty()) {
			throw new UndconException(UndconError.NAME_ALREADY_EXISTS);
		}
	}

	public void delete(long id) throws UndconException {
		permissionService.checkPermission(ResourceType.SERVICE_TYPE);
		repository.delete(id);
	}
}
