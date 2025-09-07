package com.kude.pos.config;

public class JwtConstant {
    public static final String JWT_SECRET = "YourSecretKeyForJWTSigningAndValidation12345"; // Use a strong secret key
    public static final long JWT_EXPIRATION_MS = 86400000; // 24

    public static final String JWT_HEADER = "Authorization";
    public static final String JWT_PREFIX_BEARER = "Bearer ";
}
