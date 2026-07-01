package com.example.estoque.controller;

import com.example.estoque.dto.funcionario.FuncionarioRequest;
import com.example.estoque.dto.funcionario.FuncionarioResponse;
import com.example.estoque.service.FuncionarioService;
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
@RequestMapping("/api/funcionarios")
@Validated
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public List<FuncionarioResponse> listar() {
        return funcionarioService.listar().stream().map(FuncionarioResponse::fromEntity).toList();
    }

    @GetMapping("/{id}")
    public FuncionarioResponse buscarPorId(@PathVariable @Positive Integer id) {
        return FuncionarioResponse.fromEntity(funcionarioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<FuncionarioResponse> criar(@RequestBody @Valid FuncionarioRequest request) {
        FuncionarioResponse response = FuncionarioResponse.fromEntity(funcionarioService.criar(request));
        return ResponseEntity.created(URI.create("/api/funcionarios/" + response.id())).body(response);
    }

    @PutMapping("/{id}")
    public FuncionarioResponse atualizar(@PathVariable @Positive Integer id, @RequestBody @Valid FuncionarioRequest request) {
        return FuncionarioResponse.fromEntity(funcionarioService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable @Positive Integer id) {
        funcionarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
