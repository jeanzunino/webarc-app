package com.undcon.app.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.undcon.app.enums.PaymentStatus;
import com.undcon.app.enums.ServiceOrderStatus;

@Entity
@Table(name = "ordem_servico")
public class ServiceOrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private CustomerEntity customer;

	@Column(name = "descricao", nullable = false)
	private String description;

	@Column(name = "defeito")
	private String defect;

	@Column(name = "garantia")
	private String guarantee;

	@Column(name = "laudo_tecnico")
	private String technicalReport;

	@Column(name = "observacoes")
	private String observations;

	@Column(name = "data_inicial", nullable = true)
	private Date startDate;

	@Column(name = "data_final", nullable = true)
	private Date endDate;

	@Column(name = "data_cadastro", nullable = false)
	private Date registerDate;

	@Column(name = "status_pagamento")
	private PaymentStatus paymentStatus;

	@Column(name = "status_os")
	private ServiceOrderStatus statusOs;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private UserEntity user;

	public ServiceOrderEntity() {
	}

	public ServiceOrderEntity(Long id, CustomerEntity customer, String description, String defect, String guarantee,
			String technicalReport, String observations, Date startDate, Date endDate, Date registerDate,
			PaymentStatus paymentStatus, ServiceOrderStatus statusOs, UserEntity user) {
		super();
		this.id = id;
		this.customer = customer;
		this.description = description;
		this.defect = defect;
		this.guarantee = guarantee;
		this.technicalReport = technicalReport;
		this.observations = observations;
		this.startDate = startDate;
		this.endDate = endDate;
		this.registerDate = registerDate;
		this.paymentStatus = paymentStatus;
		this.statusOs = statusOs;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDefect() {
		return defect;
	}

	public void setDefect(String defect) {
		this.defect = defect;
	}

	public String getGuarantee() {
		return guarantee;
	}

	public void setGuarantee(String guarantee) {
		this.guarantee = guarantee;
	}

	public String getTechnicalReport() {
		return technicalReport;
	}

	public void setTechnicalReport(String technicalReport) {
		this.technicalReport = technicalReport;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public ServiceOrderStatus getStatus() {
		return statusOs;
	}

	public void setStatus(ServiceOrderStatus status) {
		this.statusOs = status;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}
