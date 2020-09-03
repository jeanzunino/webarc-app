package com.undcon.app.mappers;

import org.springframework.stereotype.Component;

import com.undcon.app.dtos.PurchaseSimpleDto;
import com.undcon.app.model.PurchaseEntity;

@Component
public class PurchaseMapper {

	public PurchaseSimpleDto toSimpleDto(PurchaseEntity entity) {
		return new PurchaseSimpleDto(entity.getId(), entity.getPurchaseDate(), entity.getStatus());
	}

}
