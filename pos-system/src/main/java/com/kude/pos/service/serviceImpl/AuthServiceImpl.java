package com.kude.pos.service.serviceImpl;

import com.kude.pos.config.JwtProvider;
import com.kude.pos.dto.UserDto;
import com.kude.pos.dto.response.AuthResponse;
import com.kude.pos.model.User;
import com.kude.pos.repository.UserRepository;
import com.kude.pos.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserServiceImpl customUserService;

    @Override
    public AuthResponse signUp(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail()).orElse(null);
        if (user != null) {
            throw new RuntimeException("User with email " + userDto.getEmail() + " already exists");
        }

        return null;
    }

    @Override
    public AuthResponse login(UserDto userDto) {
        return null;
    }
}
