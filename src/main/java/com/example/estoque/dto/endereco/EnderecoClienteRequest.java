package com.example.estoque.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EnderecoClienteRequest(
        @NotBlank @Size(max = 150) String logradouro,
        @NotBlank @Size(max = 20) String numero,
        @NotBlank @Size(max = 80) String bairro,
        @NotBlank @Size(max = 80) String cidade,
        @NotBlank @Pattern(regexp = "[A-Z]{2}", message = "deve conter a sigla do estado com 2 letras maiusculas") String estado,
        @NotBlank @Pattern(regexp = "\\d{8}", message = "deve conter 8 digitos") String cep,
        @NotNull Integer clienteId
) {
}
