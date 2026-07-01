package com.example.estoque.dto.pedido;

import com.example.estoque.model.ItemPedido;
import java.math.BigDecimal;

public record ItemPedidoResponse(
        Integer produtoId,
        String produto,
        Integer quantidade,
        BigDecimal precoVenda,
        BigDecimal subtotal
) {
    public static ItemPedidoResponse fromEntity(ItemPedido itemPedido) {
        BigDecimal subtotal = itemPedido.getPrecoVenda()
                .multiply(BigDecimal.valueOf(itemPedido.getQuantidade()));
        return new ItemPedidoResponse(
                itemPedido.getProduto().getId(),
                itemPedido.getProduto().getNome(),
                itemPedido.getQuantidade(),
                itemPedido.getPrecoVenda(),
                subtotal
        );
    }
}
