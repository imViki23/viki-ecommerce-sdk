package com.viki.api.auth.dtos;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class AuthResponseDto implements Serializable {
    private String accessToken;
}
