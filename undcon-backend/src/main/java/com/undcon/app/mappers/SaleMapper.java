package com.undcon.app.mappers;

import org.springframework.stereotype.Component;

import com.undcon.app.dtos.IdDto;
import com.undcon.app.dtos.SaleSimpleDto;
import com.undcon.app.model.SaleEntity;

@Component
public class SaleMapper {

	public SaleSimpleDto toSimpleDto(SaleEntity entity) {
		IdDto customer = new IdDto(entity.getCustomer().getId());
		IdDto user = new IdDto(entity.getUser().getId());
		IdDto salesman = new IdDto(entity.getSalesman().getId());
		return new SaleSimpleDto(entity.getId(), entity.getSaleDate(), entity.getStatus(), customer, user, salesman);
	}

}
