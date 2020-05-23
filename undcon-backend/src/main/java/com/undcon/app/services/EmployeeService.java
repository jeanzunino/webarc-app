package com.undcon.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.EmployeeEntity;
import com.undcon.app.repositories.IEmployeeRepository;

@Component
public class EmployeeService extends AbstractService<EmployeeEntity>{

	@Autowired
	private IEmployeeRepository repository;
	
	public Page<EmployeeEntity> getAll(String filter, Integer page, Integer size) {
		return super.getAll(EmployeeEntity.class, filter, page, size);
	}

	@Override
	protected void validateBeforePost(EmployeeEntity entity) throws UndconException {
		super.validateBeforePost(entity);
		validateName(0L, entity.getName());
	}

	@Override
	protected void validateBeforeUpdate(EmployeeEntity entity) throws UndconException {
		super.validateBeforeUpdate(entity);
		validateName(entity.getId(), entity.getName());
	}

	@Override
	protected void validateBeforeDelete(EmployeeEntity entity) throws UndconException {
		super.validateBeforeDelete(entity);
	}

	private void validateName(Long id, String name) throws UndconException {
		List<EmployeeEntity> findByIdNotAndName = repository.findByIdNotAndNameIgnoreCase(id, name);
		if(!findByIdNotAndName.isEmpty()) {
			throw new UndconException(UndconError.NAME_ALREADY_EXISTS);
		}
	}

	public EmployeeEntity validateAndGet(Long id) throws UndconException {
		EmployeeEntity entity = findById(id);
		if(entity == null) {
			throw new UndconException(UndconError.EMPLOYEE_NOT_FOUND);
		}
		return entity;
	}

	@Override
	protected JpaRepository<EmployeeEntity, Long> getRepository() {
		return repository;
	}

	@Override
	protected ResourceType getResourceType() {
		return ResourceType.EMPLOYEE;
	}
}
