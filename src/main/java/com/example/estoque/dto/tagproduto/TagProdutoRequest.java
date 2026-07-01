package com.example.estoque.dto.tagproduto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TagProdutoRequest(
        @NotBlank @Size(max = 60) String nome
) {
}
