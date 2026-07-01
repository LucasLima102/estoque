package com.example.estoque.controller;

import com.example.estoque.dto.itempedido.ItemPedidoCrudRequest;
import com.example.estoque.dto.itempedido.ItemPedidoCrudResponse;
import com.example.estoque.service.ItemPedidoService;
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
@RequestMapping("/api/itens-pedido")
@Validated
public class ItemPedidoController {

    private final ItemPedidoService itemPedidoService;

    public ItemPedidoController(ItemPedidoService itemPedidoService) {
        this.itemPedidoService = itemPedidoService;
    }

    @GetMapping
    public List<ItemPedidoCrudResponse> listar() {
        return itemPedidoService.listar().stream().map(ItemPedidoCrudResponse::fromEntity).toList();
    }

    @GetMapping("/{id}")
    public ItemPedidoCrudResponse buscarPorId(@PathVariable @Positive Integer id) {
        return ItemPedidoCrudResponse.fromEntity(itemPedidoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ItemPedidoCrudResponse> criar(@RequestBody @Valid ItemPedidoCrudRequest request) {
        ItemPedidoCrudResponse response = ItemPedidoCrudResponse.fromEntity(itemPedidoService.criar(request));
        return ResponseEntity.created(URI.create("/api/itens-pedido/" + response.id())).body(response);
    }

    @PutMapping("/{id}")
    public ItemPedidoCrudResponse atualizar(@PathVariable @Positive Integer id, @RequestBody @Valid ItemPedidoCrudRequest request) {
        return ItemPedidoCrudResponse.fromEntity(itemPedidoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable @Positive Integer id) {
        itemPedidoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
