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

import com.undcon.app.enums.SaleStatus;

@Entity
@Table(name = "compra")
public class PurchaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "forncedor_id", nullable = false)
	private ProviderEntity provider;

	@Column(name = "data_compra", nullable = false)
	private Date purchaseDate;

	@Column(name = "faturado", nullable = false)
	private boolean billed;

	@Column(name = "status", nullable = false)
	private SaleStatus status;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private UserEntity user;

	public PurchaseEntity() {
		super();
	}

	public PurchaseEntity(Long id, ProviderEntity provider, Date purchaseDate, boolean billed, SaleStatus status,
			UserEntity user) {
		super();
		this.id = id;
		this.provider = provider;
		this.purchaseDate = purchaseDate;
		this.billed = billed;
		this.status = status;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProviderEntity getProvider() {
		return provider;
	}

	public void setProvider(ProviderEntity provider) {
		this.provider = provider;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public boolean isBilled() {
		return billed;
	}

	public void setBilled(boolean billed) {
		this.billed = billed;
	}

	public SaleStatus getStatus() {
		return status;
	}

	public void setStatus(SaleStatus status) {
		this.status = status;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
}
