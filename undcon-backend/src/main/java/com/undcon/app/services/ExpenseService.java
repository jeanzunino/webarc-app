package com.undcon.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.undcon.app.enums.ResourceType;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.ExpenseEntity;
import com.undcon.app.repositories.IExpenseRepository;

@Component
public class ExpenseService extends AbstractService<ExpenseEntity>{

	@Autowired
	private IExpenseRepository expenseRepository;
	
	public Page<ExpenseEntity> getAll(String filter, Integer page, Integer size) {
		return super.getAll(ExpenseEntity.class, filter, page, size);
	}

	@Override
	protected void validateBeforePost(ExpenseEntity entity) throws UndconException {
		super.validateBeforePost(entity);
	}

	@Override
	protected void validateBeforeUpdate(ExpenseEntity entity) throws UndconException {
		super.validateBeforeUpdate(entity);
	}

	@Override
	protected void validateBeforeDelete(ExpenseEntity entity) throws UndconException {
		super.validateBeforeDelete(entity);
	}

	@Override
	protected JpaRepository<ExpenseEntity, Long> getRepository() {
		return expenseRepository;
	}

	@Override
	protected ResourceType getResourceType() {
		return ResourceType.EXPENSE;
	}
}
