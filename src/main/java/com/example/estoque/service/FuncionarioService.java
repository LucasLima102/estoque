package com.example.estoque.service;

import com.example.estoque.dto.funcionario.FuncionarioRequest;
import com.example.estoque.exception.ResourceNotFoundException;
import com.example.estoque.model.Funcionario;
import com.example.estoque.repositories.FuncionarioRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Funcionario> listar() {
        return funcionarioRepository.findAll();
    }

    public Funcionario buscarPorId(Integer id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario nao encontrado"));
    }

    public Funcionario criar(FuncionarioRequest request) {
        Funcionario funcionario = new Funcionario();
        funcionario.setDataCriacao(LocalDateTime.now());
        aplicarDados(funcionario, request);
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario atualizar(Integer id, FuncionarioRequest request) {
        Funcionario funcionario = buscarPorId(id);
        aplicarDados(funcionario, request);
        return funcionarioRepository.save(funcionario);
    }

    public void excluir(Integer id) {
        funcionarioRepository.delete(buscarPorId(id));
    }

    private void aplicarDados(Funcionario funcionario, FuncionarioRequest request) {
        funcionario.setNome(request.nome());
        funcionario.setCpf(request.cpf());
        funcionario.setCargo(request.cargo());
        funcionario.setEmail(request.email());
    }
}
