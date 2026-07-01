package com.example.estoque.dto.cliente;

import com.example.estoque.model.Cliente;

public record ClienteResponse(
        Integer id,
        String nome,
        String cpf,
        String telefone,
        String email
) {
    public static ClienteResponse fromEntity(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getTelefone(),
                cliente.getEmail()
        );
    }
}
