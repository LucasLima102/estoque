package com.example.estoque.repositories;

import com.example.estoque.model.TagProduto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagProdutoRepository extends JpaRepository<TagProduto, Integer> {
}
