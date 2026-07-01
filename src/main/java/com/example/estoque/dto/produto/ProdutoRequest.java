package com.example.estoque.dto.produto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

public record ProdutoRequest(
        @NotBlank @Size(max = 120) String nome,
        @NotBlank @Size(max = 80) String marca,
        @NotBlank @Size(max = 80) String categoria,
        @NotBlank @Size(max = 20) String voltagem,
        @NotNull @DecimalMin(value = "0.01") BigDecimal precoBase,
        @NotNull Integer fornecedorId,
        List<Integer> promocaoIds,
        List<Integer> tagIds
) {
}
