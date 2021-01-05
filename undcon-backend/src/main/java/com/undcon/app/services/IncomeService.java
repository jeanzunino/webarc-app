package com.undcon.app.services;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.undcon.app.dtos.AmountTotalDto;
import com.undcon.app.dtos.IncomeDto;
import com.undcon.app.enums.BillingStatus;
import com.undcon.app.enums.PaymentStatus;
import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.IncomeEntity;
import com.undcon.app.model.SaleEntity;
import com.undcon.app.repositories.IIncomeRepository;
import com.undcon.app.repositories.SaleIncomeRepositoryImpl;

@Component
public class IncomeService extends AbstractService<IncomeEntity> {

	@Autowired
	private IIncomeRepository incomeRepository;

	@Autowired
	private SaleIncomeRepositoryImpl saleIncomeRepositoryImpl;

	@Autowired
	private SaleService saleService;

	public Page<IncomeEntity> getAll(String filter, Integer page, Integer size) {
		return super.getAll(IncomeEntity.class, filter, page, size);
	}

	@Override
	protected void validateBeforePost(IncomeEntity entity) throws UndconException {
		super.validateBeforePost(entity);
		if (entity.getPaymentDate() != null) {
			entity.setPaymentStatus(PaymentStatus.SETTLED);
		} else {
			entity.setPaymentStatus(PaymentStatus.PENDING);
		}
	}

	@Override
	protected void validateBeforeUpdate(IncomeEntity entity) throws UndconException {
		super.validateBeforeUpdate(entity);
		if (entity.getPaymentStatus() == PaymentStatus.CANCELED) {
			throw new UndconException(UndconError.UPDATE_ERROR_ITEM_CANCELLED);
		}
		if (entity.getPaymentDate() != null) {
			entity.setPaymentStatus(PaymentStatus.SETTLED);
		} else {
			entity.setPaymentStatus(PaymentStatus.PENDING);
		}
	}

	@Override
	protected void validateBeforeDelete(IncomeEntity entity) throws UndconException {
		super.validateBeforeDelete(entity);

		if (entity == null) {
			throw new UndconException(UndconError.INCOME_NOT_FOUND);
		}

		SaleEntity sale = entity.getSale();
		if (sale != null) {
			AmountTotalDto saleTotal = saleService.getSaleTotal(sale.getId());
			if (saleTotal.getAmountPaid() > entity.getValue()) {
				sale.setStatus(BillingStatus.BILLED);
			} else {
				sale.setStatus(BillingStatus.TO_BILL);
			}

			saleService.update(sale);
		}
	}

	/**
	 * Busca o valor total faturado para uma venda independente se está pago ou não
	 * 
	 * @param sale a venda
	 * @return o valor total faturado
	 */
	public double getIncomeValueBilledBySale(SaleEntity sale) {
		List<PaymentStatus> of = Arrays.asList(PaymentStatus.PENDING, PaymentStatus.SETTLED);
		return saleIncomeRepositoryImpl.getIncomeValueBilledBySale(sale, of);
	}

	public List<IncomeEntity> getIncomesBySale(SaleEntity sale) {
		return incomeRepository.findBySale(sale);
	}

	@Override
	protected JpaRepository<IncomeEntity, Long> getRepository() {
		return incomeRepository;
	}

	@Override
	protected ResourceType getResourceType() {
		return ResourceType.INCOME;
	}

	public IncomeEntity updateStatus(IncomeDto income) throws UndconException {
		IncomeEntity entity = incomeRepository.findOne(income.getId());
		if (entity.getPaymentStatus() == PaymentStatus.CANCELED) {
			throw new UndconException(UndconError.UPDATE_ERROR_ITEM_CANCELLED);
		}
		entity.setPaymentStatus(income.getPaymentStatus());
		if (income.getPaymentStatus() == PaymentStatus.SETTLED) {
			if (entity.getPaymentDate() == null) {
				entity.setPaymentDate(new Date(System.currentTimeMillis()));
			}
		} else {
			entity.setPaymentDate(null);
		}
		return incomeRepository.save(entity);
	}

}
