package com.undcon.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.undcon.app.enums.ResourceType;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.ExpenseEntity;
import com.undcon.app.repositories.IExpenseRepository;
import com.undcon.app.utils.PageUtils;

@Component
public class ExpenseService {

	@Autowired
	private IExpenseRepository expenseRepository;
	
	@Autowired
	private PermissionService permissionService;

	public Page<ExpenseEntity> getAll(Integer page, Integer size) {
        return expenseRepository.findAll(PageUtils.createPageRequest(page, size));
    }
	
	public ExpenseEntity findById(Long id) {
        return expenseRepository.findOne(id);
    }
	
	public ExpenseEntity persist(ExpenseEntity entity) throws UndconException {
		permissionService.checkPermission(ResourceType.EXPENSE);
		return expenseRepository.save(entity);
	}

	public ExpenseEntity update(ExpenseEntity entity) throws UndconException {
		permissionService.checkPermission(ResourceType.EXPENSE);
		return expenseRepository.save(entity);
	}

	public void delete(long id) throws UndconException {
		permissionService.checkPermission(ResourceType.EXPENSE);
		expenseRepository.delete(id);
	}
}
