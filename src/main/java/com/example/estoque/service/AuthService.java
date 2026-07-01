package com.example.estoque.service;

import com.example.estoque.dto.auth.LoginRequest;
import com.example.estoque.dto.auth.LoginResponse;
import com.example.estoque.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.usuario(), request.senha())
        );
        return new LoginResponse("Bearer", jwtService.gerarToken(request.usuario()));
    }
}
