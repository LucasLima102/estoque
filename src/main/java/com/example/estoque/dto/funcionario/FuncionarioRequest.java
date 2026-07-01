package com.example.estoque.dto.funcionario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record FuncionarioRequest(
        @NotBlank @Size(max = 120) String nome,
        @NotBlank @Pattern(regexp = "\\d{11}", message = "deve conter 11 digitos") String cpf,
        @NotBlank @Size(max = 60) String cargo,
        @NotBlank @Email @Size(max = 120) String email
) {
}
