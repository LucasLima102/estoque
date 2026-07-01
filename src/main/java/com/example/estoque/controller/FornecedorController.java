package com.example.estoque.controller;

import com.example.estoque.dto.fornecedor.FornecedorRequest;
import com.example.estoque.dto.fornecedor.FornecedorResponse;
import com.example.estoque.service.FornecedorService;
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
@RequestMapping("/api/fornecedores")
@Validated
public class FornecedorController {

    private final FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @GetMapping
    public List<FornecedorResponse> listar() {
        return fornecedorService.listar().stream().map(FornecedorResponse::fromEntity).toList();
    }

    @GetMapping("/{id}")
    public FornecedorResponse buscarPorId(@PathVariable @Positive Integer id) {
        return FornecedorResponse.fromEntity(fornecedorService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<FornecedorResponse> criar(@RequestBody @Valid FornecedorRequest request) {
        FornecedorResponse response = FornecedorResponse.fromEntity(fornecedorService.criar(request));
        return ResponseEntity.created(URI.create("/api/fornecedores/" + response.id())).body(response);
    }

    @PutMapping("/{id}")
    public FornecedorResponse atualizar(@PathVariable @Positive Integer id, @RequestBody @Valid FornecedorRequest request) {
        return FornecedorResponse.fromEntity(fornecedorService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable @Positive Integer id) {
        fornecedorService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
