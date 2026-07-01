package com.example.estoque.controller;

import com.example.estoque.dto.loginfuncionario.LoginFuncionarioCrudRequest;
import com.example.estoque.dto.loginfuncionario.LoginFuncionarioCrudResponse;
import com.example.estoque.service.LoginFuncionarioCrudService;
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
@RequestMapping("/api/logins-funcionarios")
@Validated
public class LoginFuncionarioController {

    private final LoginFuncionarioCrudService loginFuncionarioCrudService;

    public LoginFuncionarioController(LoginFuncionarioCrudService loginFuncionarioCrudService) {
        this.loginFuncionarioCrudService = loginFuncionarioCrudService;
    }

    @GetMapping
    public List<LoginFuncionarioCrudResponse> listar() {
        return loginFuncionarioCrudService.listar().stream().map(LoginFuncionarioCrudResponse::fromEntity).toList();
    }

    @GetMapping("/{id}")
    public LoginFuncionarioCrudResponse buscarPorId(@PathVariable @Positive Integer id) {
        return LoginFuncionarioCrudResponse.fromEntity(loginFuncionarioCrudService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<LoginFuncionarioCrudResponse> criar(@RequestBody @Valid LoginFuncionarioCrudRequest request) {
        LoginFuncionarioCrudResponse response = LoginFuncionarioCrudResponse.fromEntity(loginFuncionarioCrudService.criar(request));
        return ResponseEntity.created(URI.create("/api/logins-funcionarios/" + response.id())).body(response);
    }

    @PutMapping("/{id}")
    public LoginFuncionarioCrudResponse atualizar(@PathVariable @Positive Integer id, @RequestBody @Valid LoginFuncionarioCrudRequest request) {
        return LoginFuncionarioCrudResponse.fromEntity(loginFuncionarioCrudService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable @Positive Integer id) {
        loginFuncionarioCrudService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
