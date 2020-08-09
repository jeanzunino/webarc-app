package com.undcon.app.mappers;

import org.springframework.stereotype.Component;

import com.undcon.app.dtos.IdDto;
import com.undcon.app.dtos.IncomeDto;
import com.undcon.app.model.IncomeEntity;

@Component
public class IncomeMapper {

	public IncomeDto toDto(IncomeEntity entity) {
		IdDto sale = new IdDto(entity.getSale().getId());
		IdDto customer = new IdDto(entity.getCustomer().getId());
		return new IncomeDto(entity.getId(), entity.getDescription(), entity.getDuaDate(), entity.getPaymentDate(),
				entity.getValue(), entity.getPaymentStatus(), entity.getPaymentType(), sale, customer);
	}

}
