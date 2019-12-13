package com.undcon.app.dtos;

public class UserDto {

    private Long id;
    private String login;
    private EmployeeDto employee;

    public UserDto(Long id, String login, EmployeeDto employee) {
        super();
        this.id = id;
        this.login = login;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public EmployeeDto getEmployee() {
        return employee;
    }

}
