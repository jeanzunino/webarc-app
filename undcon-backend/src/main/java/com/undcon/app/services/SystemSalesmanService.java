package com.undcon.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.SystemSalesmanEntity;
import com.undcon.app.repositories.ISystemSalesmanRepository;

@Component
public class SystemSalesmanService extends AbstractService<SystemSalesmanEntity>{

	@Autowired
	private ISystemSalesmanRepository repository;
	
	public Page<SystemSalesmanEntity> getAll(String filter, Integer page, Integer size) {
		return super.getAll(SystemSalesmanEntity.class, filter, page, size);
	}

	@Override
	protected void validateBeforePost(SystemSalesmanEntity entity) throws UndconException {
		super.validateBeforePost(entity);
		validateName(0L, entity.getName());
	}

	@Override
	protected void validateBeforeUpdate(SystemSalesmanEntity entity) throws UndconException {
		super.validateBeforeUpdate(entity);
		validateName(entity.getId(), entity.getName());
	}

	@Override
	protected void validateBeforeDelete(SystemSalesmanEntity entity) throws UndconException {
		super.validateBeforeDelete(entity);
	}

	private void validateName(Long id, String name) throws UndconException {
		List<SystemSalesmanEntity> findByIdNotAndName = repository.findByIdNotAndNameIgnoreCase(id, name);
		if(!findByIdNotAndName.isEmpty()) {
			throw new UndconException(UndconError.NAME_ALREADY_EXISTS);
		}
	}

	public SystemSalesmanEntity validateAndGet(Long id) throws UndconException {
		SystemSalesmanEntity entity = findById(id);
		if(entity == null) {
			throw new UndconException(UndconError.SALESMAN_NOT_FOUND);
		}
		return entity;
	}

	@Override
	protected JpaRepository<SystemSalesmanEntity, Long> getRepository() {
		return repository;
	}

	@Override
	protected ResourceType getResourceType() {
		return ResourceType.SYSTEM_SALESMAN;
	}
}
