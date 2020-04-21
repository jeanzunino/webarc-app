package com.undcon.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.CustomerEntity;
import com.undcon.app.repositories.ICustomerRepository;
import com.undcon.app.utils.PageUtils;

@Component
public class CustomerService {

	@Autowired
	private ICustomerRepository customerRepository;
	
	@Autowired
	private PermissionService permissionService;

	public Page<CustomerEntity> getAll(String name, Integer page, Integer size) {
		if(StringUtils.isEmpty(name)) {
			return customerRepository.findAll(PageUtils.createPageRequest(page, size));
		}
        return customerRepository.findAllByNameContainingIgnoreCase(name, PageUtils.createPageRequest(page, size));
    }
	
	public CustomerEntity findById(Long id) {
        return customerRepository.findOne(id);
    }
	
	public CustomerEntity persist(CustomerEntity entity) throws UndconException {
		permissionService.checkPermission(ResourceType.CUSTOMER);
		validateName(0L, entity.getName());
		return customerRepository.save(entity);
	}

	public CustomerEntity update(CustomerEntity entity) throws UndconException {
		permissionService.checkPermission(ResourceType.CUSTOMER);
		validateName(entity.getId(), entity.getName());
		return customerRepository.save(entity);
	}

	private void validateName(Long id, String name) throws UndconException {
		List<CustomerEntity> findByIdNotAndName = customerRepository.findByIdNotAndName(id, name);
		if(!findByIdNotAndName.isEmpty()) {
			throw new UndconException(UndconError.NAME_ALREADY_EXISTS);
		}
	}

	public void delete(long id) throws UndconException{
		permissionService.checkPermission(ResourceType.CUSTOMER);
		customerRepository.delete(id);
	}
}
