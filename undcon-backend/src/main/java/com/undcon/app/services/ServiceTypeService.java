package com.undcon.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.ServiceTypeEntity;
import com.undcon.app.repositories.IServiceTypeRepository;

@Component
public class ServiceTypeService extends AbstractService<ServiceTypeEntity> {

	@Autowired
	private IServiceTypeRepository repository;

	public Page<ServiceTypeEntity> getAll(String filter, Integer page, Integer size) {
		return super.getAll(ServiceTypeEntity.class, filter, page, size);
	}
	
	@Override
	protected void validateBeforePost(ServiceTypeEntity entity) throws UndconException {
		super.validateBeforePost(entity);
		validateName(0L, entity.getName());
	}

	@Override
	protected void validateBeforeUpdate(ServiceTypeEntity entity) throws UndconException {
		super.validateBeforeUpdate(entity);
		validateName(entity.getId(), entity.getName());
	}

	private void validateName(Long id, String name) throws UndconException {
		List<ServiceTypeEntity> findByIdNotAndName = repository.findByIdNotAndName(id, name);
		if (!findByIdNotAndName.isEmpty()) {
			throw new UndconException(UndconError.NAME_ALREADY_EXISTS);
		}
	}

	@Override
	protected JpaRepository<ServiceTypeEntity, Long> getRepository() {
		return repository;
	}

	@Override
	protected ResourceType getResourceType() {
		return ResourceType.SERVICE_TYPE;
	}
}
