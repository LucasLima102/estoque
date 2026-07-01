package com.example.estoque.service;

import com.example.estoque.dto.tagproduto.TagProdutoRequest;
import com.example.estoque.exception.ResourceNotFoundException;
import com.example.estoque.model.TagProduto;
import com.example.estoque.repositories.TagProdutoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TagProdutoService {

    private final TagProdutoRepository tagProdutoRepository;

    public TagProdutoService(TagProdutoRepository tagProdutoRepository) {
        this.tagProdutoRepository = tagProdutoRepository;
    }

    public List<TagProduto> listar() {
        return tagProdutoRepository.findAll();
    }

    public TagProduto buscarPorId(Integer id) {
        return tagProdutoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tag de produto nao encontrada"));
    }

    public TagProduto criar(TagProdutoRequest request) {
        TagProduto tag = new TagProduto();
        aplicarDados(tag, request);
        return tagProdutoRepository.save(tag);
    }

    public TagProduto atualizar(Integer id, TagProdutoRequest request) {
        TagProduto tag = buscarPorId(id);
        aplicarDados(tag, request);
        return tagProdutoRepository.save(tag);
    }

    public void excluir(Integer id) {
        tagProdutoRepository.delete(buscarPorId(id));
    }

    private void aplicarDados(TagProduto tag, TagProdutoRequest request) {
        tag.setNome(request.nome());
    }
}
