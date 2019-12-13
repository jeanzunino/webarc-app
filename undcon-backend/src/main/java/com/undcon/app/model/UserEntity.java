package com.undcon.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "senha")
    private String password;

    @OneToOne
    @JoinColumn(name = "empregado_id", nullable = true)
    private EmployeeEntity employee;

    @OneToOne
    @JoinColumn(name = "permissao_id", nullable = true)
    private PermissionEntity permission;

    protected UserEntity() {
    }

    public UserEntity(Long id, String login, String password, EmployeeEntity employee, PermissionEntity permission) {
        super();
        this.id = id;
        this.login = login;
        this.password = password;
        this.employee = employee;
        this.permission = permission;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PermissionEntity getPermission() {
        return permission;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

}
