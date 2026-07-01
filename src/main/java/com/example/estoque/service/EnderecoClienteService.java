package com.example.estoque.service;

import com.example.estoque.dto.endereco.EnderecoClienteRequest;
import com.example.estoque.exception.ResourceNotFoundException;
import com.example.estoque.model.Cliente;
import com.example.estoque.model.EnderecoCliente;
import com.example.estoque.repositories.ClienteRepository;
import com.example.estoque.repositories.EnderecoClienteRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EnderecoClienteService {

    private final EnderecoClienteRepository enderecoClienteRepository;
    private final ClienteRepository clienteRepository;

    public EnderecoClienteService(EnderecoClienteRepository enderecoClienteRepository, ClienteRepository clienteRepository) {
        this.enderecoClienteRepository = enderecoClienteRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<EnderecoCliente> listar() {
        return enderecoClienteRepository.findAll();
    }

    public EnderecoCliente buscarPorId(Integer id) {
        return enderecoClienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereco nao encontrado"));
    }

    public EnderecoCliente criar(EnderecoClienteRequest request) {
        EnderecoCliente endereco = new EnderecoCliente();
        aplicarDados(endereco, request);
        return enderecoClienteRepository.save(endereco);
    }

    public EnderecoCliente atualizar(Integer id, EnderecoClienteRequest request) {
        EnderecoCliente endereco = buscarPorId(id);
        aplicarDados(endereco, request);
        return enderecoClienteRepository.save(endereco);
    }

    public void excluir(Integer id) {
        enderecoClienteRepository.delete(buscarPorId(id));
    }

    private void aplicarDados(EnderecoCliente endereco, EnderecoClienteRequest request) {
        Cliente cliente = clienteRepository.findById(request.clienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente nao encontrado"));
        endereco.setLogradouro(request.logradouro());
        endereco.setNumero(request.numero());
        endereco.setBairro(request.bairro());
        endereco.setCidade(request.cidade());
        endereco.setEstado(request.estado());
        endereco.setCep(request.cep());
        endereco.setCliente(cliente);
    }
}
