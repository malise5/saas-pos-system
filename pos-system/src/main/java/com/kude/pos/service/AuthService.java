package com.kude.pos.service;

import com.kude.pos.dto.UserDto;
import com.kude.pos.dto.response.AuthResponse;

public interface AuthService {

    AuthResponse signUp(UserDto userDto);
    AuthResponse login(UserDto userDto);
}
