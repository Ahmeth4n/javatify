package com.magnakod.entity;

import jakarta.persistence.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Entity
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
