package com.example.estoque.dto.pedido;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StatusPedidoRequest(
        @NotBlank @Size(max = 30) String statusPedido
) {
}
