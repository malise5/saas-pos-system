package com.kude.pos.service.serviceImpl;

import com.kude.pos.config.JwtProvider;
import com.kude.pos.domain.UserRole;
import com.kude.pos.dto.UserDto;
import com.kude.pos.dto.response.AuthResponse;
import com.kude.pos.mapper.UserMapper;
import com.kude.pos.model.User;
import com.kude.pos.repository.UserRepository;
import com.kude.pos.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
        if (userDto.getRole().equals(UserRole.ROLE_ADMIN)) {
            throw new RuntimeException("Cannot assign ROLE_ADMIN during sign up");
        }

        //create new user
        User newUser = new User();
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setFullName(userDto.getFullName());
        newUser.setPhoneNumber(userDto.getPhoneNumber());
        newUser.setRole(userDto.getRole());
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(newUser);

        //authenticate user
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = jwtProvider.generateToken(userDto.getEmail(), userDto.getRole().name());

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwtToken);
        authResponse.setMessage("success sign up");
        authResponse.setUser(UserMapper.toDto(savedUser));

        return authResponse;
    }

    @Override
    public AuthResponse login(UserDto userDto) {
        return null;
    }
}
