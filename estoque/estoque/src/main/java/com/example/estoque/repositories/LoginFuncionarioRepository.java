package com.example.estoque.repositories;

import com.example.estoque.model.LoginFuncionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginFuncionarioRepository extends JpaRepository<LoginFuncionario, Integer> {
}
