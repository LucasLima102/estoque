package com.example.estoque.service;

import com.example.estoque.dto.entrega.EntregaRequest;
import com.example.estoque.exception.ResourceNotFoundException;
import com.example.estoque.model.EnderecoCliente;
import com.example.estoque.model.Entrega;
import com.example.estoque.model.Pedido;
import com.example.estoque.repositories.EnderecoClienteRepository;
import com.example.estoque.repositories.EntregaRepository;
import com.example.estoque.repositories.PedidoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EntregaService {

    private final EntregaRepository entregaRepository;
    private final PedidoRepository pedidoRepository;
    private final EnderecoClienteRepository enderecoClienteRepository;

    public EntregaService(
            EntregaRepository entregaRepository,
            PedidoRepository pedidoRepository,
            EnderecoClienteRepository enderecoClienteRepository
    ) {
        this.entregaRepository = entregaRepository;
        this.pedidoRepository = pedidoRepository;
        this.enderecoClienteRepository = enderecoClienteRepository;
    }

    public List<Entrega> listar() {
        return entregaRepository.findAll();
    }

    public Entrega buscarPorId(Integer id) {
        return entregaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entrega nao encontrada"));
    }

    public Entrega criar(EntregaRequest request) {
        Entrega entrega = new Entrega();
        aplicarDados(entrega, request);
        return entregaRepository.save(entrega);
    }

    public Entrega atualizar(Integer id, EntregaRequest request) {
        Entrega entrega = buscarPorId(id);
        aplicarDados(entrega, request);
        return entregaRepository.save(entrega);
    }

    public void excluir(Integer id) {
        entregaRepository.delete(buscarPorId(id));
    }

    private void aplicarDados(Entrega entrega, EntregaRequest request) {
        Pedido pedido = pedidoRepository.findById(request.pedidoId())
                .orElseThrow(() -> new ResourceNotFoundException("Pedido nao encontrado"));
        EnderecoCliente endereco = enderecoClienteRepository.findById(request.enderecoClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Endereco nao encontrado"));
        entrega.setCodigoRastreio(request.codigoRastreio());
        entrega.setDataPrevisao(request.dataPrevisao());
        entrega.setStatusEntrega(request.statusEntrega());
        entrega.setPedido(pedido);
        entrega.setEnderecoCliente(endereco);
    }
}
