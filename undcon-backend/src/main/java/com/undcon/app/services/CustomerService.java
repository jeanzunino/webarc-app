package com.undcon.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.CustomerEntity;
import com.undcon.app.repositories.ICustomerRepository;

@Component
public class CustomerService extends AbstractService<CustomerEntity> {

	@Autowired
	private ICustomerRepository customerRepository;
	
	public Page<CustomerEntity> getAll(String filter, Integer page, Integer size) {
		return super.getAll(CustomerEntity.class, filter, page, size);
	}

	@Override
	protected void validateBeforePost(CustomerEntity entity) throws UndconException {
		super.validateBeforePost(entity);
		validateName(0L, entity.getName());
	}

	@Override
	protected void validateBeforeUpdate(CustomerEntity entity) throws UndconException {
		super.validateBeforeUpdate(entity);
		validateName(entity.getId(), entity.getName());
	}

	@Override
	protected void validateBeforeDelete(CustomerEntity entity) throws UndconException {
		super.validateBeforeDelete(entity);
	}

	private void validateName(Long id, String name) throws UndconException {
		List<CustomerEntity> findByIdNotAndName = customerRepository.findByIdNotAndNameIgnoreCase(id, name);
		if(!findByIdNotAndName.isEmpty()) {
			throw new UndconException(UndconError.NAME_ALREADY_EXISTS);
		}
	}

	@Override
	protected JpaRepository<CustomerEntity, Long> getRepository() {
		return customerRepository;
	}

	@Override
	protected ResourceType getResourceType() {
		return ResourceType.CUSTOMER;
	}
}
