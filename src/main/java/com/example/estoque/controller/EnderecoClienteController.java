package com.example.estoque.controller;

import com.example.estoque.dto.endereco.EnderecoClienteRequest;
import com.example.estoque.dto.endereco.EnderecoClienteResponse;
import com.example.estoque.service.EnderecoClienteService;
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
@RequestMapping("/api/enderecos")
@Validated
public class EnderecoClienteController {

    private final EnderecoClienteService enderecoClienteService;

    public EnderecoClienteController(EnderecoClienteService enderecoClienteService) {
        this.enderecoClienteService = enderecoClienteService;
    }

    @GetMapping
    public List<EnderecoClienteResponse> listar() {
        return enderecoClienteService.listar().stream().map(EnderecoClienteResponse::fromEntity).toList();
    }

    @GetMapping("/{id}")
    public EnderecoClienteResponse buscarPorId(@PathVariable @Positive Integer id) {
        return EnderecoClienteResponse.fromEntity(enderecoClienteService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<EnderecoClienteResponse> criar(@RequestBody @Valid EnderecoClienteRequest request) {
        EnderecoClienteResponse response = EnderecoClienteResponse.fromEntity(enderecoClienteService.criar(request));
        return ResponseEntity.created(URI.create("/api/enderecos/" + response.id())).body(response);
    }

    @PutMapping("/{id}")
    public EnderecoClienteResponse atualizar(@PathVariable @Positive Integer id, @RequestBody @Valid EnderecoClienteRequest request) {
        return EnderecoClienteResponse.fromEntity(enderecoClienteService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable @Positive Integer id) {
        enderecoClienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
