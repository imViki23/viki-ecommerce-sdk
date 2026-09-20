package com.viki.api.auth.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "users", schema = "profiles")
public class UserEntity {
    @Id
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String emailId;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;
}
