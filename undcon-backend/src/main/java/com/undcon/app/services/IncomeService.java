package com.undcon.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.undcon.app.enums.ResourceType;
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

	public Page<IncomeEntity> getAll(String filter, Integer page, Integer size) {
		return super.getAll(IncomeEntity.class, filter, page, size);
	}

	@Override
	protected void validateBeforePost(IncomeEntity entity) throws UndconException {
		super.validateBeforePost(entity);
	}

	@Override
	protected void validateBeforeUpdate(IncomeEntity entity) throws UndconException {
		super.validateBeforeUpdate(entity);
	}

	@Override
	protected void validateBeforeDelete(IncomeEntity entity) throws UndconException {
		super.validateBeforeDelete(entity);
	}

	/**
	 * Busca o valor total faturado para uma venda independente se está pago ou não
	 * 
	 * @param sale a venda
	 * @return o valor total faturado
	 */
	public double getIncomeValueBilledBySale(SaleEntity sale) {
		Optional<Boolean> of = Optional.empty();
		return saleIncomeRepositoryImpl.getIncomeValueBilledBySale(sale, of);
	}

	@Override
	protected JpaRepository<IncomeEntity, Long> getRepository() {
		return incomeRepository;
	}

	@Override
	protected ResourceType getResourceType() {
		return ResourceType.INCOME;
	}

}
