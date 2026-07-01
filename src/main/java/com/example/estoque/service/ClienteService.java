package com.example.estoque.service;

import com.example.estoque.dto.cliente.ClienteRequest;
import com.example.estoque.exception.ResourceNotFoundException;
import com.example.estoque.model.Cliente;
import com.example.estoque.repositories.ClienteRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente nao encontrado"));
    }

    public Cliente criar(ClienteRequest request) {
        Cliente cliente = new Cliente();
        aplicarDados(cliente, request);
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Integer id, ClienteRequest request) {
        Cliente cliente = buscarPorId(id);
        aplicarDados(cliente, request);
        return clienteRepository.save(cliente);
    }

    public void excluir(Integer id) {
        Cliente cliente = buscarPorId(id);
        clienteRepository.delete(cliente);
    }

    private void aplicarDados(Cliente cliente, ClienteRequest request) {
        cliente.setNome(request.nome());
        cliente.setCpf(request.cpf());
        cliente.setTelefone(request.telefone());
        cliente.setEmail(request.email());
    }
}
