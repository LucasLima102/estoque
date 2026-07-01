package com.example.estoque.dto.pedido;

import com.example.estoque.model.Pedido;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponse(
        Integer id,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao,
        String statusPedido,
        Integer clienteId,
        String cliente,
        Integer funcionarioId,
        String funcionario,
        List<ItemPedidoResponse> itens,
        BigDecimal total
) {
    public static PedidoResponse fromEntity(Pedido pedido) {
        List<ItemPedidoResponse> itens = pedido.getItensPedido() == null
                ? List.of()
                : pedido.getItensPedido().stream().map(ItemPedidoResponse::fromEntity).toList();
        BigDecimal total = itens.stream()
                .map(ItemPedidoResponse::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new PedidoResponse(
                pedido.getId(),
                pedido.getDataCriacao(),
                pedido.getDataAtualizacao(),
                pedido.getStatusPedido(),
                pedido.getCliente().getId(),
                pedido.getCliente().getNome(),
                pedido.getFuncionario().getId(),
                pedido.getFuncionario().getNome(),
                itens,
                total
        );
    }
}
