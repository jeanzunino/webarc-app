package com.undcon.app.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.undcon.app.dtos.EmployeeDto;
import com.undcon.app.dtos.UserDto;
import com.undcon.app.model.UserEntity;

@Component
public class UserMapper {

    @Autowired
    private EmployeeMapper employeeMapper;
    
    public UserDto toDto(UserEntity entity) {
        EmployeeDto employee = employeeMapper.toDto(entity.getEmployee());
        return new UserDto(entity.getId(), entity.getLogin(), employee );
    }
    
    public List<UserDto> toDto(List<UserEntity> entityList) {
        return entityList.stream().map(entity -> toDto(entity)).collect(Collectors.toList());
    }
}
