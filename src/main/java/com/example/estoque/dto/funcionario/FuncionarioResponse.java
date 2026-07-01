package com.example.estoque.dto.funcionario;

import com.example.estoque.model.Funcionario;
import java.time.LocalDateTime;

public record FuncionarioResponse(
        Integer id,
        String nome,
        String cpf,
        String cargo,
        String email,
        LocalDateTime dataCriacao
) {
    public static FuncionarioResponse fromEntity(Funcionario funcionario) {
        return new FuncionarioResponse(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCpf(),
                funcionario.getCargo(),
                funcionario.getEmail(),
                funcionario.getDataCriacao()
        );
    }
}
