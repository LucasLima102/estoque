package com.example.estoque.repositories;

import com.example.estoque.model.LoginFuncionario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginFuncionarioRepository extends JpaRepository<LoginFuncionario, Integer> {
    Optional<LoginFuncionario> findByUsuario(String usuario);
}
