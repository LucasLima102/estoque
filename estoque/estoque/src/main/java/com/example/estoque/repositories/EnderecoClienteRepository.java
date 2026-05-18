package com.example.estoque.repositories;

import com.example.estoque.model.EnderecoCliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoClienteRepository extends JpaRepository<EnderecoCliente, Integer> {
}
