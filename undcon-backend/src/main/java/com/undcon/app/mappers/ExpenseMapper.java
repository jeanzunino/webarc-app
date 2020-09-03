package com.undcon.app.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.undcon.app.dtos.ExpenseDto;
import com.undcon.app.dtos.IdDto;
import com.undcon.app.dtos.IncomeDto;
import com.undcon.app.dtos.PersonSimpleDto;
import com.undcon.app.model.ExpenseEntity;
import com.undcon.app.model.IncomeEntity;

@Component
public class ExpenseMapper {

	public ExpenseDto toDto(ExpenseEntity entity) {
		IdDto purchase = new IdDto(entity.getPurchase().getId());
		PersonSimpleDto provider = new PersonSimpleDto(entity.getProvider().getId(), entity.getProvider().getName());
		return new ExpenseDto(entity.getId(), entity.getDescription(), entity.getDuaDate(), entity.getPaymentDate(),
				entity.getValue(), entity.getPaymentStatus(), entity.getPaymentType(), purchase, provider);
	}
	
	public List<ExpenseDto> toDto(List<ExpenseEntity> entityList) {
		return entityList.stream().map(entity -> toDto(entity)).collect(Collectors.toList());
	}

}
