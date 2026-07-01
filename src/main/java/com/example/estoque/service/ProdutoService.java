package com.example.estoque.service;

import com.example.estoque.dto.produto.ProdutoRequest;
import com.example.estoque.exception.ResourceNotFoundException;
import com.example.estoque.model.Fornecedor;
import com.example.estoque.model.Produto;
import com.example.estoque.model.Promocao;
import com.example.estoque.model.TagProduto;
import com.example.estoque.repositories.FornecedorRepository;
import com.example.estoque.repositories.ProdutoRepository;
import com.example.estoque.repositories.PromocaoRepository;
import com.example.estoque.repositories.TagProdutoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final FornecedorRepository fornecedorRepository;
    private final PromocaoRepository promocaoRepository;
    private final TagProdutoRepository tagProdutoRepository;

    public ProdutoService(
            ProdutoRepository produtoRepository,
            FornecedorRepository fornecedorRepository,
            PromocaoRepository promocaoRepository,
            TagProdutoRepository tagProdutoRepository
    ) {
        this.produtoRepository = produtoRepository;
        this.fornecedorRepository = fornecedorRepository;
        this.promocaoRepository = promocaoRepository;
        this.tagProdutoRepository = tagProdutoRepository;
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Integer id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto nao encontrado"));
    }

    public Produto criar(ProdutoRequest request) {
        Produto produto = new Produto();
        aplicarDados(produto, request);
        return produtoRepository.save(produto);
    }

    public Produto atualizar(Integer id, ProdutoRequest request) {
        Produto produto = buscarPorId(id);
        aplicarDados(produto, request);
        return produtoRepository.save(produto);
    }

    public void excluir(Integer id) {
        Produto produto = buscarPorId(id);
        produtoRepository.delete(produto);
    }

    private void aplicarDados(Produto produto, ProdutoRequest request) {
        Fornecedor fornecedor = fornecedorRepository.findById(request.fornecedorId())
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor nao encontrado"));
        List<Promocao> promocoes = request.promocaoIds() == null
                ? List.of()
                : promocaoRepository.findAllById(request.promocaoIds());
        List<TagProduto> tags = request.tagIds() == null
                ? List.of()
                : tagProdutoRepository.findAllById(request.tagIds());

        produto.setNome(request.nome());
        produto.setMarca(request.marca());
        produto.setCategoria(request.categoria());
        produto.setVoltagem(request.voltagem());
        produto.setPrecoBase(request.precoBase());
        produto.setFornecedor(fornecedor);
        produto.setPromocoes(promocoes);
        produto.setTags(tags);
    }
}
