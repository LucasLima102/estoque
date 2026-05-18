package com.example.estoque.repositories;

import com.example.estoque.model.Promocao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromocaoRepository extends JpaRepository<Promocao, Integer> {
}
