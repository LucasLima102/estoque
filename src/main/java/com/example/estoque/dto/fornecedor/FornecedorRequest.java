package com.example.estoque.dto.fornecedor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record FornecedorRequest(
        @NotBlank @Size(max = 120) String nome,
        @NotBlank @Email @Size(max = 120) String email,
        @NotBlank @Pattern(regexp = "\\d{14}", message = "deve conter 14 digitos") String cnpj,
        @Size(max = 20) String telefone
) {
}
