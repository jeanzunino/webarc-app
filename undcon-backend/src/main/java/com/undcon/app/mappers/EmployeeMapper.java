package com.undcon.app.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.undcon.app.dtos.EmployeeDto;
import com.undcon.app.model.EmployeeEntity;

@Component
public class EmployeeMapper {

    public EmployeeDto toDto(EmployeeEntity entity) {
        return new EmployeeDto(entity.getId(), entity.getName(), entity.getPhone());
    }

    public List<EmployeeDto> toDto(List<EmployeeEntity> entityList) {
        return entityList.stream().map(entity -> toDto(entity)).collect(Collectors.toList());
    }
}
