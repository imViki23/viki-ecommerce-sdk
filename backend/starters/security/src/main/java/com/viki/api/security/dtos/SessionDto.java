package com.viki.api.security.dtos;

import lombok.Data;

@Data
public class SessionDto {
    private String emailId;
    private String userName;
    private String role;
}
