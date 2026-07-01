package com.example.estoque.dto.entrega;

import com.example.estoque.model.Entrega;
import java.time.LocalDate;

public record EntregaResponse(
        Integer id,
        String codigoRastreio,
        LocalDate dataPrevisao,
        String statusEntrega,
        Integer pedidoId,
        Integer enderecoClienteId,
        String enderecoResumo
) {
    public static EntregaResponse fromEntity(Entrega entrega) {
        String enderecoResumo = entrega.getEnderecoCliente().getLogradouro()
                + ", "
                + entrega.getEnderecoCliente().getNumero();
        return new EntregaResponse(
                entrega.getId(),
                entrega.getCodigoRastreio(),
                entrega.getDataPrevisao(),
                entrega.getStatusEntrega(),
                entrega.getPedido().getId(),
                entrega.getEnderecoCliente().getId(),
                enderecoResumo
        );
    }
}
