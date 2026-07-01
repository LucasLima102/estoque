package com.example.estoque.dto.fornecedor;

import com.example.estoque.model.Fornecedor;

public record FornecedorResponse(
        Integer id,
        String nome,
        String email,
        String cnpj,
        String telefone
) {
    public static FornecedorResponse fromEntity(Fornecedor fornecedor) {
        return new FornecedorResponse(
                fornecedor.getId(),
                fornecedor.getNome(),
                fornecedor.getEmail(),
                fornecedor.getCnpj(),
                fornecedor.getTelefone()
        );
    }
}
