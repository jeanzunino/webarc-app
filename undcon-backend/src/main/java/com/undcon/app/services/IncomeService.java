package com.undcon.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.undcon.app.enums.ResourceType;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.IncomeEntity;
import com.undcon.app.repositories.IIncomeRepository;
import com.undcon.app.utils.PageUtils;

@Component
public class IncomeService {

	@Autowired
	private IIncomeRepository incomeRepository;
	
	@Autowired
	private PermissionService permissionService;

	public Page<IncomeEntity> getAll(Integer page, Integer size) {
        return incomeRepository.findAll(PageUtils.createPageRequest(page, size));
    }
	
	public IncomeEntity findById(Long id) {
        return incomeRepository.findOne(id);
    }
	
	public IncomeEntity persist(IncomeEntity entity) throws UndconException {
		permissionService.checkPermission(ResourceType.INCOME);
		return incomeRepository.save(entity);
	}

	public IncomeEntity update(IncomeEntity entity) throws UndconException {
		permissionService.checkPermission(ResourceType.INCOME);
		return incomeRepository.save(entity);
	}

	public void delete(long id) throws UndconException {
		permissionService.checkPermission(ResourceType.INCOME);
		incomeRepository.delete(id);
	}
}
