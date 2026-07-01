package com.example.estoque.service;

import com.example.estoque.dto.pedido.ItemPedidoRequest;
import com.example.estoque.dto.pedido.PedidoRequest;
import com.example.estoque.dto.pedido.PedidoUpdateRequest;
import com.example.estoque.exception.ResourceNotFoundException;
import com.example.estoque.model.Cliente;
import com.example.estoque.model.Funcionario;
import com.example.estoque.model.ItemPedido;
import com.example.estoque.model.Pedido;
import com.example.estoque.model.Produto;
import com.example.estoque.repositories.ClienteRepository;
import com.example.estoque.repositories.FuncionarioRepository;
import com.example.estoque.repositories.ItemPedidoRepository;
import com.example.estoque.repositories.PedidoRepository;
import com.example.estoque.repositories.ProdutoRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    public PedidoService(
            PedidoRepository pedidoRepository,
            ClienteRepository clienteRepository,
            FuncionarioRepository funcionarioRepository,
            ProdutoRepository produtoRepository,
            ItemPedidoRepository itemPedidoRepository
    ) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.produtoRepository = produtoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Integer id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido nao encontrado"));
    }

    @Transactional
    public Pedido criar(PedidoRequest request) {
        Cliente cliente = clienteRepository.findById(request.clienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente nao encontrado"));
        Funcionario funcionario = funcionarioRepository.findById(request.funcionarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario nao encontrado"));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setFuncionario(funcionario);
        pedido.setStatusPedido("CRIADO");
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setDataAtualizacao(LocalDateTime.now());
        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        List<ItemPedido> itens = new ArrayList<>();
        for (ItemPedidoRequest itemRequest : request.itens()) {
            Produto produto = produtoRepository.findById(itemRequest.produtoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produto nao encontrado"));

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedidoSalvo);
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(itemRequest.quantidade());
            itemPedido.setPrecoVenda(produto.getPrecoBase());
            itens.add(itemPedidoRepository.save(itemPedido));
        }

        pedidoSalvo.setItensPedido(itens);
        return pedidoSalvo;
    }

    public Pedido atualizarStatus(Integer id, String statusPedido) {
        Pedido pedido = buscarPorId(id);
        pedido.setStatusPedido(statusPedido);
        pedido.setDataAtualizacao(LocalDateTime.now());
        return pedidoRepository.save(pedido);
    }

    public Pedido atualizar(Integer id, PedidoUpdateRequest request) {
        Pedido pedido = buscarPorId(id);
        Cliente cliente = clienteRepository.findById(request.clienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente nao encontrado"));
        Funcionario funcionario = funcionarioRepository.findById(request.funcionarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario nao encontrado"));

        pedido.setCliente(cliente);
        pedido.setFuncionario(funcionario);
        pedido.setStatusPedido(request.statusPedido());
        pedido.setDataAtualizacao(LocalDateTime.now());
        return pedidoRepository.save(pedido);
    }

    public void excluir(Integer id) {
        Pedido pedido = buscarPorId(id);
        pedidoRepository.delete(pedido);
    }
}
