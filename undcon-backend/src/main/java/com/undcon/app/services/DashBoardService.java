package com.undcon.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.undcon.app.repositories.ICustomerRepository;
import com.undcon.app.repositories.IProductRepository;
import com.undcon.app.repositories.IProviderRepository;

@Component
public class DashBoardService {

	@Autowired
	private ICustomerRepository customerRepository;
	
	@Autowired
	private IProductRepository productRepository;
	
	@Autowired
	private IProviderRepository providerRepository;
	
	public long countCustomersTotal() {
		return customerRepository.count();
	}

	public long countProductsTotal() {
		return productRepository.count();
	}
	
	public long countProvidersTotal() {
		return providerRepository.count();
	}
}
