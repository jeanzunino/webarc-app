package com.undcon.app.mappers;

import org.springframework.stereotype.Component;

import com.undcon.app.dtos.SaleSimpleDto;
import com.undcon.app.model.SaleEntity;

@Component
public class SaleMapper {

	public SaleSimpleDto toSimpleDto(SaleEntity entity) {
		return new SaleSimpleDto(entity.getId(), entity.getSaleDate(), entity.getStatus());
	}

}
