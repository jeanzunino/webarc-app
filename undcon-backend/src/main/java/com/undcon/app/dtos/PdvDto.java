package com.undcon.app.dtos;

import java.sql.Date;

public class PdvDto {
	private Long id;

	private UserDto user;

	private UserDto responsibleUserInOpening;

	private UserDto responsibleUserInClosing;

	private Date openingDate;

	private Date closingDate;

	private double openingValue;

	private Double closingValue;

	public PdvDto() {
	}

	public PdvDto(Long id, UserDto user, UserDto responsibleUserInOpening, UserDto responsibleUserInClosing,
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

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public UserDto getResponsibleUserInOpening() {
		return responsibleUserInOpening;
	}

	public void setResponsibleUserInOpening(UserDto responsibleUserInOpening) {
		this.responsibleUserInOpening = responsibleUserInOpening;
	}

	public UserDto getResponsibleUserInClosing() {
		return responsibleUserInClosing;
	}

	public void setResponsibleUserInClosing(UserDto responsibleUserInClosing) {
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
