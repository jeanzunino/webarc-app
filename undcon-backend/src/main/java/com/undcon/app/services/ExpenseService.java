package com.undcon.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.undcon.app.enums.ResourseType;
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

	public List<ExpenseEntity> getAll(Integer page, Integer size) {
        return expenseRepository.findAll(PageUtils.createPageRequest(page, size)).getContent();
    }
	
	public ExpenseEntity findById(Long id) {
        return expenseRepository.findOne(id);
    }
	
	public ExpenseEntity persist(ExpenseEntity entity) throws UndconException {
		permissionService.checkPermission(ResourseType.EXPENSE);
		return expenseRepository.save(entity);
	}

	public ExpenseEntity update(ExpenseEntity entity) throws UndconException {
		permissionService.checkPermission(ResourseType.EXPENSE);
		return expenseRepository.save(entity);
	}

	public void delete(long id) throws UndconException {
		permissionService.checkPermission(ResourseType.EXPENSE);
		expenseRepository.delete(id);
	}
}
