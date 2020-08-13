package com.undcon.app.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.undcon.app.dtos.IdDto;
import com.undcon.app.dtos.IncomeDto;
import com.undcon.app.dtos.PersonSimpleDto;
import com.undcon.app.model.IncomeEntity;

@Component
public class IncomeMapper {

	public IncomeDto toDto(IncomeEntity entity) {
		IdDto sale = new IdDto(entity.getSale().getId());
		PersonSimpleDto customer = new PersonSimpleDto(entity.getCustomer().getId(), entity.getCustomer().getName());
		return new IncomeDto(entity.getId(), entity.getDescription(), entity.getDuaDate(), entity.getPaymentDate(),
				entity.getValue(), entity.getPaymentStatus(), entity.getPaymentType(), sale, customer);
	}
	
	public List<IncomeDto> toDto(List<IncomeEntity> entityList) {
		return entityList.stream().map(entity -> toDto(entity)).collect(Collectors.toList());
	}

}
