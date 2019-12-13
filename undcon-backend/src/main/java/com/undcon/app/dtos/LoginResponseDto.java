package com.undcon.app.dtos;

public class LoginResponseDto {

    private String tenant;
    private String token;
    private boolean resetPassword;
    private UserDto user;

    public LoginResponseDto() {
    }

    public LoginResponseDto(String tenant, String token, boolean resetPassword, UserDto user) {
        super();
        this.tenant = tenant;
        this.token = token;
        this.resetPassword = resetPassword;
        this.user = user;
    }

    public String getTenant() {
        return tenant;
    }

    public String getToken() {
        return token;
    }

    public UserDto getUser() {
        return user;
    }

    public boolean isResetPassword() {
        return resetPassword;
    }
}
