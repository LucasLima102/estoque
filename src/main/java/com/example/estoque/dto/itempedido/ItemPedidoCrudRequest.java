package com.example.estoque.dto.itempedido;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ItemPedidoCrudRequest(
        @NotNull @DecimalMin("0.01") BigDecimal precoVenda,
        @NotNull @Min(1) Integer quantidade,
        @NotNull Integer produtoId,
        @NotNull Integer pedidoId
) {
}
