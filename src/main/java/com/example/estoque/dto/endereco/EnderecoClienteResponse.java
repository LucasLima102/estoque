package com.example.estoque.dto.endereco;

import com.example.estoque.model.EnderecoCliente;

public record EnderecoClienteResponse(
        Integer id,
        String logradouro,
        String numero,
        String bairro,
        String cidade,
        String estado,
        String cep,
        Integer clienteId,
        String cliente
) {
    public static EnderecoClienteResponse fromEntity(EnderecoCliente endereco) {
        return new EnderecoClienteResponse(
                endereco.getId(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep(),
                endereco.getCliente().getId(),
                endereco.getCliente().getNome()
        );
    }
}
