package com.example.estoque.dto.pedido;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ItemPedidoRequest(
        @NotNull Integer produtoId,
        @NotNull @Min(1) Integer quantidade
) {
}
