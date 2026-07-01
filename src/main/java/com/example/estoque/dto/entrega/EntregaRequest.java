package com.example.estoque.dto.entrega;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record EntregaRequest(
        @NotBlank @Size(max = 40) String codigoRastreio,
        @NotNull LocalDate dataPrevisao,
        @NotBlank @Size(max = 30) String statusEntrega,
        @NotNull Integer pedidoId,
        @NotNull Integer enderecoClienteId
) {
}
