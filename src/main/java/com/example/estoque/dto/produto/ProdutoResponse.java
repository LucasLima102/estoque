package com.example.estoque.dto.produto;

import com.example.estoque.dto.fornecedor.FornecedorResponse;
import com.example.estoque.model.Produto;
import java.math.BigDecimal;
import java.util.List;

public record ProdutoResponse(
        Integer id,
        String nome,
        String marca,
        String categoria,
        String voltagem,
        BigDecimal precoBase,
        FornecedorResponse fornecedor,
        List<String> promocoes,
        List<String> tags
) {
    public static ProdutoResponse fromEntity(Produto produto) {
        List<String> promocoes = produto.getPromocoes() == null
                ? List.of()
                : produto.getPromocoes().stream().map(promocao -> promocao.getNome()).toList();
        List<String> tags = produto.getTags() == null
                ? List.of()
                : produto.getTags().stream().map(tag -> tag.getNome()).toList();

        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getMarca(),
                produto.getCategoria(),
                produto.getVoltagem(),
                produto.getPrecoBase(),
                FornecedorResponse.fromEntity(produto.getFornecedor()),
                promocoes,
                tags
        );
    }
}
