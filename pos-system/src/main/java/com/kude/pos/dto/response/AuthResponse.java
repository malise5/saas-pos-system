package com.kude.pos.dto.response;


import com.kude.pos.dto.UserDto;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String tokenType = "Bearer";
    private String message;
    private UserDto user;

}
