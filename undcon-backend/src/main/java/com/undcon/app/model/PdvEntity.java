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

@Entity
@Table(name = "pdv")
public class PdvEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name = "usuario_responsavel_abertura_id", nullable = false)
	private UserEntity responsibleUserInOpening;

	@ManyToOne
	@JoinColumn(name = "usuario_responsavel_fechamento_id", nullable = true)
	private UserEntity responsibleUserInClosing;

	@Column(name = "data_abertura", nullable = false)
	private Date openingDate;

	@Column(name = "data_fechamento", nullable = true)
	private Date closingDate;

	@Column(name = "valor_abertura")
	private double openingValue;

	@Column(name = "valor_fechamento")
	private Double closingValue;

	protected PdvEntity() {
	}

	public PdvEntity(Long id, UserEntity user, UserEntity responsibleUserInOpening, UserEntity responsibleUserInClosing,
			Date openingDate, Date closingDate, double openingValue, Double closingValue) {
		super();
		this.id = id;
		this.user = user;
		this.responsibleUserInOpening = responsibleUserInOpening;
		this.responsibleUserInClosing = responsibleUserInClosing;
		this.openingDate = openingDate;
		this.closingDate = closingDate;
		this.openingValue = openingValue;
		this.closingValue = closingValue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public UserEntity getResponsibleUserInOpening() {
		return responsibleUserInOpening;
	}

	public void setResponsibleUserInOpening(UserEntity responsibleUserInOpening) {
		this.responsibleUserInOpening = responsibleUserInOpening;
	}

	public UserEntity getResponsibleUserInClosing() {
		return responsibleUserInClosing;
	}

	public void setResponsibleUserInClosing(UserEntity responsibleUserInClosing) {
		this.responsibleUserInClosing = responsibleUserInClosing;
	}

	public Date getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public double getOpeningValue() {
		return openingValue;
	}

	public void setOpeningValue(double openingValue) {
		this.openingValue = openingValue;
	}

	public Double getClosingValue() {
		return closingValue;
	}

	public void setClosingValue(Double closingValue) {
		this.closingValue = closingValue;
	}

}