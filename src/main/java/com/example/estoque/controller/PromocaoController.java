package com.example.estoque.controller;

import com.example.estoque.dto.promocao.PromocaoRequest;
import com.example.estoque.dto.promocao.PromocaoResponse;
import com.example.estoque.service.PromocaoService;
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
@RequestMapping("/api/promocoes")
@Validated
public class PromocaoController {

    private final PromocaoService promocaoService;

    public PromocaoController(PromocaoService promocaoService) {
        this.promocaoService = promocaoService;
    }

    @GetMapping
    public List<PromocaoResponse> listar() {
        return promocaoService.listar().stream().map(PromocaoResponse::fromEntity).toList();
    }

    @GetMapping("/{id}")
    public PromocaoResponse buscarPorId(@PathVariable @Positive Integer id) {
        return PromocaoResponse.fromEntity(promocaoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<PromocaoResponse> criar(@RequestBody @Valid PromocaoRequest request) {
        PromocaoResponse response = PromocaoResponse.fromEntity(promocaoService.criar(request));
        return ResponseEntity.created(URI.create("/api/promocoes/" + response.id())).body(response);
    }

    @PutMapping("/{id}")
    public PromocaoResponse atualizar(@PathVariable @Positive Integer id, @RequestBody @Valid PromocaoRequest request) {
        return PromocaoResponse.fromEntity(promocaoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable @Positive Integer id) {
        promocaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
