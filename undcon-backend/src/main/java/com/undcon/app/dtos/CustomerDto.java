package com.undcon.app.dtos;

public class CustomerDto {

    private Long id;

    private String name;

    private String phone;

    public CustomerDto() {
	}
    
    public CustomerDto(Long id, String name, String phone) {
        super();
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    
    public Long getId() {
        return id;
    }

    
    public String getName() {
        return name;
    }

    
    public String getPhone() {
        return phone;
    }
    
    
}
