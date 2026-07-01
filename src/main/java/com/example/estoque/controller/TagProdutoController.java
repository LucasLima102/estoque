package com.example.estoque.controller;

import com.example.estoque.dto.tagproduto.TagProdutoRequest;
import com.example.estoque.dto.tagproduto.TagProdutoResponse;
import com.example.estoque.service.TagProdutoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tags-produto")
@Validated
public class TagProdutoController {

    private final TagProdutoService tagProdutoService;

    public TagProdutoController(TagProdutoService tagProdutoService) {
        this.tagProdutoService = tagProdutoService;
    }

    @GetMapping
    public List<TagProdutoResponse> listar() {
        return tagProdutoService.listar().stream().map(TagProdutoResponse::fromEntity).toList();
    }

    @GetMapping("/{id}")
    public TagProdutoResponse buscarPorId(@PathVariable @Positive Integer id) {
        return TagProdutoResponse.fromEntity(tagProdutoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<TagProdutoResponse> criar(@RequestBody @Valid TagProdutoRequest request) {
        TagProdutoResponse response = TagProdutoResponse.fromEntity(tagProdutoService.criar(request));
        return ResponseEntity.created(URI.create("/api/tags-produto/" + response.id())).body(response);
    }

    @PutMapping("/{id}")
    public TagProdutoResponse atualizar(@PathVariable @Positive Integer id, @RequestBody @Valid TagProdutoRequest request) {
        return TagProdutoResponse.fromEntity(tagProdutoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable @Positive Integer id) {
        tagProdutoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
