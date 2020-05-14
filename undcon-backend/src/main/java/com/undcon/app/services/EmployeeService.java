package com.undcon.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.EmployeeEntity;
import com.undcon.app.repositories.IEmployeeRepository;
import com.undcon.app.utils.PageUtils;

@Component
public class EmployeeService {

	@Autowired
	private IEmployeeRepository repository;
	
	@Autowired
	private PermissionService permissionService;

	public Page<EmployeeEntity> getAll(String name, Integer page, Integer size) {
		if(StringUtils.isEmpty(name)) {
			return repository.findAll(PageUtils.createPageRequest(page, size));
		}
        return repository.findAllByNameContainingIgnoreCase(name, PageUtils.createPageRequest(page, size));
    }
	
	public EmployeeEntity findById(Long id) {
        return repository.findOne(id);
    }
	
	public EmployeeEntity persist(EmployeeEntity entity) throws UndconException {
		permissionService.checkPermission(ResourceType.EMPLOYEE);
		validateName(0L, entity.getName());
		return repository.save(entity);
	}

	public EmployeeEntity update(EmployeeEntity entity) throws UndconException {
		permissionService.checkPermission(ResourceType.EMPLOYEE);
		validateName(entity.getId(), entity.getName());
		return repository.save(entity);
	}

	private void validateName(Long id, String name) throws UndconException {
		List<EmployeeEntity> findByIdNotAndName = repository.findByIdNotAndNameIgnoreCase(id, name);
		if(!findByIdNotAndName.isEmpty()) {
			throw new UndconException(UndconError.NAME_ALREADY_EXISTS);
		}
	}

	public void delete(long id) throws UndconException {
		permissionService.checkPermission(ResourceType.EMPLOYEE);
		repository.delete(id);
	}

	public EmployeeEntity validateAndGet(Long id) throws UndconException {
		EmployeeEntity entity = findById(id);
		if(entity == null) {
			throw new UndconException(UndconError.EMPLOYEE_NOT_FOUND);
		}
		return entity;
	}
}
