package com.estoque.estoque_api.service;

import com.estoque.estoque_api.model.Estoque;
import com.estoque.estoque_api.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class EstoqueService {

    @Autowired
    EstoqueRepository estoqueRepository;

    public Optional<Estoque> findById(Long id) {
        return estoqueRepository.findById(id);
    }

    public List<Estoque> listarEstoque() {
        return estoqueRepository.findAll();
    }

    public Estoque criarEstoque(Estoque estoque) {
        return estoqueRepository.save(estoque);
    }

    public Estoque atualizar(Long id, Estoque estoqueAtualizado) {
        return estoqueRepository.findById(id)
                .map(estoque -> {
                    estoque.setQuantidade(estoqueAtualizado.getQuantidade());
                    estoque.setProduto_id(estoqueAtualizado.getProduto_id());
                    return estoqueRepository.save(estoque);
                }).orElseThrow();

    }

    public void removerEstoque(Long id) {
        estoqueRepository.deleteById(id);
    }
}