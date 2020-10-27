package com.undcon.app.services;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.undcon.app.dtos.ExpenseDto;
import com.undcon.app.enums.PaymentStatus;
import com.undcon.app.enums.ResourceType;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.ExpenseEntity;
import com.undcon.app.model.PurchaseEntity;
import com.undcon.app.repositories.IExpenseRepository;
import com.undcon.app.repositories.PurchaseExpenseRepositoryImpl;

@Component
public class ExpenseService extends AbstractService<ExpenseEntity> {

	@Autowired
	private IExpenseRepository expenseRepository;

	@Autowired
	private PurchaseExpenseRepositoryImpl purchaseExpenseRepositoryImpl;

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

	/**
	 * Busca o valor total faturado para uma venda independente se está pago ou não
	 * 
	 * @param purchase a compra
	 * @return o valor total faturado
	 */
	public double getExpenseValueBilledByPurchase(PurchaseEntity purchase) {
		List<PaymentStatus> of = Arrays.asList(PaymentStatus.PENDING, PaymentStatus.SETTLED);
		return purchaseExpenseRepositoryImpl.getExpenseValueBilledByPurchase(purchase, of);
	}

	public List<ExpenseEntity> getExpensesByPurchase(PurchaseEntity purchase) {
		return expenseRepository.findByPurchase(purchase);
	}

	@Override
	protected JpaRepository<ExpenseEntity, Long> getRepository() {
		return expenseRepository;
	}

	@Override
	protected ResourceType getResourceType() {
		return ResourceType.EXPENSE;
	}

	public ExpenseEntity updateStatus(ExpenseDto expense) {
		ExpenseEntity entity = expenseRepository.findOne(expense.getId());
		entity.setPaymentStatus(expense.getPaymentStatus());
		if (expense.getPaymentStatus() == PaymentStatus.SETTLED) {
			if (entity.getPaymentDate() == null) {
				entity.setPaymentDate(new Date(System.currentTimeMillis()));
			}
		} else {
			entity.setPaymentDate(null);
		}
		return expenseRepository.save(entity);
	}
}
