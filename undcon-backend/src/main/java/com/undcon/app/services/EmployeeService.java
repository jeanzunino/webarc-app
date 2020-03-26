package com.undcon.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.undcon.app.enums.ResourseType;
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

	public List<EmployeeEntity> getAll(String name, Integer page, Integer size) {
		if(StringUtils.isEmpty(name)) {
			return repository.findAll(PageUtils.createPageRequest(page, size)).getContent();
		}
        return repository.findAllByName(name, PageUtils.createPageRequest(page, size)).getContent();
    }
	
	public EmployeeEntity findById(Long id) {
        return repository.findOne(id);
    }
	
	public EmployeeEntity persist(EmployeeEntity entity) throws UndconException {
		permissionService.checkPermission(ResourseType.EMPLOYEE);
		validateName(0L, entity.getName());
		return repository.save(entity);
	}

	public EmployeeEntity update(EmployeeEntity entity) throws UndconException {
		permissionService.checkPermission(ResourseType.EMPLOYEE);
		validateName(entity.getId(), entity.getName());
		return repository.save(entity);
	}

	private void validateName(Long id, String name) throws UndconException {
		List<EmployeeEntity> findByIdNotAndName = repository.findByIdNotAndName(id, name);
		if(!findByIdNotAndName.isEmpty()) {
			throw new UndconException(UndconError.NAME_ALREADY_EXISTS);
		}
	}

	public void delete(long id) {
		permissionService.checkPermission(ResourseType.EMPLOYEE);
		repository.delete(id);
	}
}
