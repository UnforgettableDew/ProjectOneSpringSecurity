package com.example.projectone.entities;

import com.example.projectone.enums.Status;
import com.example.projectone.security.ApplicationUserRole;

import javax.persistence.*;

@Entity(name = "users")
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(name = "id")
    private Long id;
    private String password;
    private String username;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private ApplicationUserRole applicationUserRole;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public UserEntity(Long id, String password, String username, ApplicationUserRole applicationUserRole, Status status) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.applicationUserRole = applicationUserRole;
        this.status = status;
    }

    public UserEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ApplicationUserRole getApplicationUserRole() {
        return applicationUserRole;
    }

    public void setApplicationUserRole(ApplicationUserRole applicationUserRole) {
        this.applicationUserRole = applicationUserRole;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
