package com.viki.api.auth.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthRequestDto implements Serializable {
    private String emailId;
    private String password;
}
