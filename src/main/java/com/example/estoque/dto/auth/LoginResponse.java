package com.example.estoque.dto.auth;

public record LoginResponse(
        String tipo,
        String token
) {
}
