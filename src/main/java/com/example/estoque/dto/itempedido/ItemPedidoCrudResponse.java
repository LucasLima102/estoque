package com.example.estoque.dto.itempedido;

import com.example.estoque.model.ItemPedido;
import java.math.BigDecimal;

public record ItemPedidoCrudResponse(
        Integer id,
        BigDecimal precoVenda,
        Integer quantidade,
        Integer produtoId,
        String produto,
        Integer pedidoId,
        BigDecimal subtotal
) {
    public static ItemPedidoCrudResponse fromEntity(ItemPedido itemPedido) {
        BigDecimal subtotal = itemPedido.getPrecoVenda()
                .multiply(BigDecimal.valueOf(itemPedido.getQuantidade()));
        return new ItemPedidoCrudResponse(
                itemPedido.getId(),
                itemPedido.getPrecoVenda(),
                itemPedido.getQuantidade(),
                itemPedido.getProduto().getId(),
                itemPedido.getProduto().getNome(),
                itemPedido.getPedido().getId(),
                subtotal
        );
    }
}
