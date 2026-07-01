package com.example.estoque.controller;

import com.example.estoque.dto.cliente.ClienteRequest;
import com.example.estoque.dto.cliente.ClienteResponse;
import com.example.estoque.service.ClienteService;
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
@RequestMapping("/api/clientes")
@Validated
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<ClienteResponse> listar() {
        return clienteService.listar().stream().map(ClienteResponse::fromEntity).toList();
    }

    @GetMapping("/{id}")
    public ClienteResponse buscarPorId(@PathVariable @Positive Integer id) {
        return ClienteResponse.fromEntity(clienteService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> criar(@RequestBody @Valid ClienteRequest request) {
        ClienteResponse response = ClienteResponse.fromEntity(clienteService.criar(request));
        return ResponseEntity.created(URI.create("/api/clientes/" + response.id())).body(response);
    }

    @PutMapping("/{id}")
    public ClienteResponse atualizar(@PathVariable @Positive Integer id, @RequestBody @Valid ClienteRequest request) {
        return ClienteResponse.fromEntity(clienteService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable @Positive Integer id) {
        clienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
