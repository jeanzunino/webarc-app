package com.undcon.app.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item_venda_servico")
public class SaleItemServiceEntity extends SaleItemEntity {

	@ManyToOne
	@JoinColumn(name = "servico_id", nullable = false)
	private ServiceTypeEntity service;

	public SaleItemServiceEntity() {
	}

	public SaleItemServiceEntity(Long id, ServiceTypeEntity service, SaleEntity sale, UserEntity user,
			EmployeeEntity salesman, double price, long quantity) {
		super(id, sale, user, salesman, price, quantity);
		this.service = service;
	}

	public ServiceTypeEntity getService() {
		return service;
	}

	public void setService(ServiceTypeEntity service) {
		this.service = service;
	}

}
