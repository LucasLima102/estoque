package com.example.estoque.dto.pedido;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record PedidoRequest(
        @NotNull Integer clienteId,
        @NotNull Integer funcionarioId,
        @NotEmpty List<@Valid ItemPedidoRequest> itens
) {
}
