package com.example.estoque.controller;

import com.example.estoque.dto.entrega.EntregaRequest;
import com.example.estoque.dto.entrega.EntregaResponse;
import com.example.estoque.service.EntregaService;
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
@RequestMapping("/api/entregas")
@Validated
public class EntregaController {

    private final EntregaService entregaService;

    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @GetMapping
    public List<EntregaResponse> listar() {
        return entregaService.listar().stream().map(EntregaResponse::fromEntity).toList();
    }

    @GetMapping("/{id}")
    public EntregaResponse buscarPorId(@PathVariable @Positive Integer id) {
        return EntregaResponse.fromEntity(entregaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<EntregaResponse> criar(@RequestBody @Valid EntregaRequest request) {
        EntregaResponse response = EntregaResponse.fromEntity(entregaService.criar(request));
        return ResponseEntity.created(URI.create("/api/entregas/" + response.id())).body(response);
    }

    @PutMapping("/{id}")
    public EntregaResponse atualizar(@PathVariable @Positive Integer id, @RequestBody @Valid EntregaRequest request) {
        return EntregaResponse.fromEntity(entregaService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable @Positive Integer id) {
        entregaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
