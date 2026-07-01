package com.example.estoque.service;

import com.example.estoque.dto.fornecedor.FornecedorRequest;
import com.example.estoque.exception.ResourceNotFoundException;
import com.example.estoque.model.Fornecedor;
import com.example.estoque.repositories.FornecedorRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    public List<Fornecedor> listar() {
        return fornecedorRepository.findAll();
    }

    public Fornecedor buscarPorId(Integer id) {
        return fornecedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor nao encontrado"));
    }

    public Fornecedor criar(FornecedorRequest request) {
        Fornecedor fornecedor = new Fornecedor();
        aplicarDados(fornecedor, request);
        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor atualizar(Integer id, FornecedorRequest request) {
        Fornecedor fornecedor = buscarPorId(id);
        aplicarDados(fornecedor, request);
        return fornecedorRepository.save(fornecedor);
    }

    public void excluir(Integer id) {
        Fornecedor fornecedor = buscarPorId(id);
        fornecedorRepository.delete(fornecedor);
    }

    private void aplicarDados(Fornecedor fornecedor, FornecedorRequest request) {
        fornecedor.setNome(request.nome());
        fornecedor.setEmail(request.email());
        fornecedor.setCnpj(request.cnpj());
        fornecedor.setTelefone(request.telefone());
    }
}
