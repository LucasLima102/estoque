package com.example.estoque.service;

import com.example.estoque.dto.itempedido.ItemPedidoCrudRequest;
import com.example.estoque.exception.ResourceNotFoundException;
import com.example.estoque.model.ItemPedido;
import com.example.estoque.model.Pedido;
import com.example.estoque.model.Produto;
import com.example.estoque.repositories.ItemPedidoRepository;
import com.example.estoque.repositories.PedidoRepository;
import com.example.estoque.repositories.ProdutoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;

    public ItemPedidoService(
            ItemPedidoRepository itemPedidoRepository,
            ProdutoRepository produtoRepository,
            PedidoRepository pedidoRepository
    ) {
        this.itemPedidoRepository = itemPedidoRepository;
        this.produtoRepository = produtoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    public List<ItemPedido> listar() {
        return itemPedidoRepository.findAll();
    }

    public ItemPedido buscarPorId(Integer id) {
        return itemPedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item de pedido nao encontrado"));
    }

    public ItemPedido criar(ItemPedidoCrudRequest request) {
        ItemPedido itemPedido = new ItemPedido();
        aplicarDados(itemPedido, request);
        return itemPedidoRepository.save(itemPedido);
    }

    public ItemPedido atualizar(Integer id, ItemPedidoCrudRequest request) {
        ItemPedido itemPedido = buscarPorId(id);
        aplicarDados(itemPedido, request);
        return itemPedidoRepository.save(itemPedido);
    }

    public void excluir(Integer id) {
        itemPedidoRepository.delete(buscarPorId(id));
    }

    private void aplicarDados(ItemPedido itemPedido, ItemPedidoCrudRequest request) {
        Produto produto = produtoRepository.findById(request.produtoId())
                .orElseThrow(() -> new ResourceNotFoundException("Produto nao encontrado"));
        Pedido pedido = pedidoRepository.findById(request.pedidoId())
                .orElseThrow(() -> new ResourceNotFoundException("Pedido nao encontrado"));
        itemPedido.setPrecoVenda(request.precoVenda());
        itemPedido.setQuantidade(request.quantidade());
        itemPedido.setProduto(produto);
        itemPedido.setPedido(pedido);
    }
}
