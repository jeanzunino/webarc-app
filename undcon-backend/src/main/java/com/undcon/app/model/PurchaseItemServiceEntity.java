package com.undcon.app.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item_compra_servico")
public class PurchaseItemServiceEntity extends PurchaseItemEntity {

	@ManyToOne
	@JoinColumn(name = "servico_id", nullable = false)
	private ServiceTypeEntity service;

	public PurchaseItemServiceEntity() {
	}

	public PurchaseItemServiceEntity(Long id, ServiceTypeEntity service, PurchaseEntity purchase, UserEntity user,
			EmployeeEntity salesman, double price, long quantity) {
		super(id, purchase, user, salesman, price, quantity);
		this.service = service;
	}

	public ServiceTypeEntity getService() {
		return service;
	}

	public void setService(ServiceTypeEntity service) {
		this.service = service;
	}

}
