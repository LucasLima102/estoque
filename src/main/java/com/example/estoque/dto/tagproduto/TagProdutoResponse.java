package com.example.estoque.dto.tagproduto;

import com.example.estoque.model.TagProduto;

public record TagProdutoResponse(
        Integer id,
        String nome
) {
    public static TagProdutoResponse fromEntity(TagProduto tagProduto) {
        return new TagProdutoResponse(tagProduto.getId(), tagProduto.getNome());
    }
}
