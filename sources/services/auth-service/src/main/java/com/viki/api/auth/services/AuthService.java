package com.viki.api.auth.services;

import com.viki.api.auth.dtos.AuthRequestDto;
import com.viki.api.auth.dtos.AuthResponseDto;

public interface AuthService {
    AuthResponseDto login(AuthRequestDto authRequestDto);
}
