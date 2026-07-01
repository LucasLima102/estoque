package com.example.estoque.service;

import com.example.estoque.dto.loginfuncionario.LoginFuncionarioCrudRequest;
import com.example.estoque.exception.ResourceNotFoundException;
import com.example.estoque.model.Funcionario;
import com.example.estoque.model.LoginFuncionario;
import com.example.estoque.repositories.FuncionarioRepository;
import com.example.estoque.repositories.LoginFuncionarioRepository;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginFuncionarioCrudService {

    private final LoginFuncionarioRepository loginFuncionarioRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginFuncionarioCrudService(
            LoginFuncionarioRepository loginFuncionarioRepository,
            FuncionarioRepository funcionarioRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.loginFuncionarioRepository = loginFuncionarioRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<LoginFuncionario> listar() {
        return loginFuncionarioRepository.findAll();
    }

    public LoginFuncionario buscarPorId(Integer id) {
        return loginFuncionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Login de funcionario nao encontrado"));
    }

    public LoginFuncionario criar(LoginFuncionarioCrudRequest request) {
        LoginFuncionario login = new LoginFuncionario();
        aplicarDados(login, request);
        return loginFuncionarioRepository.save(login);
    }

    public LoginFuncionario atualizar(Integer id, LoginFuncionarioCrudRequest request) {
        LoginFuncionario login = buscarPorId(id);
        aplicarDados(login, request);
        return loginFuncionarioRepository.save(login);
    }

    public void excluir(Integer id) {
        loginFuncionarioRepository.delete(buscarPorId(id));
    }

    private void aplicarDados(LoginFuncionario login, LoginFuncionarioCrudRequest request) {
        Funcionario funcionario = funcionarioRepository.findById(request.funcionarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario nao encontrado"));
        login.setUsuario(request.usuario());
        login.setSenha(passwordEncoder.encode(request.senha()));
        login.setFuncionario(funcionario);
    }
}
