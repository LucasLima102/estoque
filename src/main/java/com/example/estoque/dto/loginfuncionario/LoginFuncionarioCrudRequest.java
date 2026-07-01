package com.example.estoque.dto.loginfuncionario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginFuncionarioCrudRequest(
        @NotBlank @Size(max = 60) String usuario,
        @NotBlank @Size(min = 6, max = 60) String senha,
        @NotNull Integer funcionarioId
) {
}
