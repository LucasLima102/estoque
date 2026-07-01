package com.example.estoque.dto.promocao;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public record PromocaoRequest(
        @NotBlank @Size(max = 100) String nome,
        @Size(max = 255) String descricao,
        @NotNull LocalDate dataInicio,
        @NotNull LocalDate dataFim,
        @NotNull @DecimalMin("0.01") @DecimalMax("100.00") BigDecimal percentualDesconto
) {
}
