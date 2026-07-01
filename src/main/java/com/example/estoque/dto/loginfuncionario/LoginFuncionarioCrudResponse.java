package com.example.estoque.dto.loginfuncionario;

import com.example.estoque.model.LoginFuncionario;
import java.time.LocalDateTime;

public record LoginFuncionarioCrudResponse(
        Integer id,
        String usuario,
        LocalDateTime ultimoLogin,
        Integer funcionarioId,
        String funcionario
) {
    public static LoginFuncionarioCrudResponse fromEntity(LoginFuncionario login) {
        return new LoginFuncionarioCrudResponse(
                login.getId(),
                login.getUsuario(),
                login.getUltimoLogin(),
                login.getFuncionario().getId(),
                login.getFuncionario().getNome()
        );
    }
}
