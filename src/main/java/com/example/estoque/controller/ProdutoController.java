package com.example.estoque.controller;

import com.example.estoque.dto.produto.ProdutoRequest;
import com.example.estoque.dto.produto.ProdutoResponse;
import com.example.estoque.service.ProdutoService;
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
@RequestMapping("/api/produtos")
@Validated
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<ProdutoResponse> listar() {
        return produtoService.listar().stream().map(ProdutoResponse::fromEntity).toList();
    }

    @GetMapping("/{id}")
    public ProdutoResponse buscarPorId(@PathVariable @Positive Integer id) {
        return ProdutoResponse.fromEntity(produtoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> criar(@RequestBody @Valid ProdutoRequest request) {
        ProdutoResponse response = ProdutoResponse.fromEntity(produtoService.criar(request));
        return ResponseEntity.created(URI.create("/api/produtos/" + response.id())).body(response);
    }

    @PutMapping("/{id}")
    public ProdutoResponse atualizar(@PathVariable @Positive Integer id, @RequestBody @Valid ProdutoRequest request) {
        return ProdutoResponse.fromEntity(produtoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable @Positive Integer id) {
        produtoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
