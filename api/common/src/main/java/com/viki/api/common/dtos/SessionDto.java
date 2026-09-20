package com.viki.api.common.dtos;

import lombok.Data;

@Data
public class SessionDto {
    private String emailId;
    private String userName;
    private String role;
}
