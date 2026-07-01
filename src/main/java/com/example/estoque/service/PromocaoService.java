package com.example.estoque.service;

import com.example.estoque.dto.promocao.PromocaoRequest;
import com.example.estoque.exception.ResourceNotFoundException;
import com.example.estoque.model.Promocao;
import com.example.estoque.repositories.PromocaoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PromocaoService {

    private final PromocaoRepository promocaoRepository;

    public PromocaoService(PromocaoRepository promocaoRepository) {
        this.promocaoRepository = promocaoRepository;
    }

    public List<Promocao> listar() {
        return promocaoRepository.findAll();
    }

    public Promocao buscarPorId(Integer id) {
        return promocaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Promocao nao encontrada"));
    }

    public Promocao criar(PromocaoRequest request) {
        Promocao promocao = new Promocao();
        aplicarDados(promocao, request);
        return promocaoRepository.save(promocao);
    }

    public Promocao atualizar(Integer id, PromocaoRequest request) {
        Promocao promocao = buscarPorId(id);
        aplicarDados(promocao, request);
        return promocaoRepository.save(promocao);
    }

    public void excluir(Integer id) {
        promocaoRepository.delete(buscarPorId(id));
    }

    private void aplicarDados(Promocao promocao, PromocaoRequest request) {
        promocao.setNome(request.nome());
        promocao.setDescricao(request.descricao());
        promocao.setDataInicio(request.dataInicio());
        promocao.setDataFim(request.dataFim());
        promocao.setPercentualDesconto(request.percentualDesconto());
    }
}
