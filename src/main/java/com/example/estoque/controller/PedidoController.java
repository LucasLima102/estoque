package com.example.estoque.controller;

import com.example.estoque.dto.pedido.PedidoRequest;
import com.example.estoque.dto.pedido.PedidoResponse;
import com.example.estoque.dto.pedido.PedidoUpdateRequest;
import com.example.estoque.dto.pedido.StatusPedidoRequest;
import com.example.estoque.service.PedidoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedidos")
@Validated
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<PedidoResponse> listar() {
        return pedidoService.listar().stream().map(PedidoResponse::fromEntity).toList();
    }

    @GetMapping("/{id}")
    public PedidoResponse buscarPorId(@PathVariable @Positive Integer id) {
        return PedidoResponse.fromEntity(pedidoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> criar(@RequestBody @Valid PedidoRequest request) {
        PedidoResponse response = PedidoResponse.fromEntity(pedidoService.criar(request));
        return ResponseEntity.created(URI.create("/api/pedidos/" + response.id())).body(response);
    }

    @PatchMapping("/{id}/status")
    public PedidoResponse atualizarStatus(
            @PathVariable @Positive Integer id,
            @RequestBody @Valid StatusPedidoRequest request
    ) {
        return PedidoResponse.fromEntity(pedidoService.atualizarStatus(id, request.statusPedido()));
    }

    @PutMapping("/{id}")
    public PedidoResponse atualizar(@PathVariable @Positive Integer id, @RequestBody @Valid PedidoUpdateRequest request) {
        return PedidoResponse.fromEntity(pedidoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable @Positive Integer id) {
        pedidoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
