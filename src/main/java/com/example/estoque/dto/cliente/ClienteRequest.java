package com.example.estoque.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClienteRequest(
        @NotBlank @Size(max = 120) String nome,
        @NotBlank @Pattern(regexp = "\\d{11}", message = "deve conter 11 digitos") String cpf,
        @Size(max = 20) String telefone,
        @NotBlank @Email @Size(max = 120) String email
) {
}
