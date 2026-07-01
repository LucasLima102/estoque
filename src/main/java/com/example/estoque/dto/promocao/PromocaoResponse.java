package com.example.estoque.dto.promocao;

import com.example.estoque.model.Promocao;
import java.math.BigDecimal;
import java.time.LocalDate;

public record PromocaoResponse(
        Integer id,
        String nome,
        String descricao,
        LocalDate dataInicio,
        LocalDate dataFim,
        BigDecimal percentualDesconto
) {
    public static PromocaoResponse fromEntity(Promocao promocao) {
        return new PromocaoResponse(
                promocao.getId(),
                promocao.getNome(),
                promocao.getDescricao(),
                promocao.getDataInicio(),
                promocao.getDataFim(),
                promocao.getPercentualDesconto()
        );
    }
}
