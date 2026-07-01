package com.example.estoque.dto.pedido;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PedidoUpdateRequest(
        @NotNull Integer clienteId,
        @NotNull Integer funcionarioId,
        @NotBlank @Size(max = 30) String statusPedido
) {
}
