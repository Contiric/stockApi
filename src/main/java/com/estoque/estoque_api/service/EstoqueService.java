package com.estoque.estoque_api.service;

import com.estoque.estoque_api.dto.EstoqueDTO;
import com.estoque.estoque_api.exception.BusinessException;
import com.estoque.estoque_api.mapper.EstoqueMapper;
import com.estoque.estoque_api.model.Estoque;
import com.estoque.estoque_api.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstoqueService {

    @Autowired
    EstoqueRepository estoqueRepository;

    @Autowired
    EstoqueMapper estoqueMapper;

    public EstoqueDTO findById(Long id) {
            Estoque estoque = estoqueRepository.findById(id)
                    .orElseThrow(() -> new BusinessException("Estoque não encontrado com ID: " + id));
            return estoqueMapper.toDTO(estoque);
    }

    public List<EstoqueDTO> listarEstoque() {
        List<Estoque> estoques = estoqueRepository.findAll();

        return estoques.stream()
                .map(estoqueMapper::toDTO)
                .collect(Collectors.toList());
    }

    public EstoqueDTO criarEstoque(Estoque estoque) {
        Estoque newEstoque = estoqueRepository.save(estoque);

        return estoqueMapper.toDTO(newEstoque);
    }

    public EstoqueDTO atualizar(Long id, EstoqueDTO estoqueAtualizado) {
        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Estoque não encontrado com ID: " + id));

        estoque.setQuantidade(estoqueAtualizado.getQuantidade());

        Estoque newEstoque = estoqueRepository.save(estoque);

        return estoqueMapper.toDTO(newEstoque);
    }

    public void removerEstoque(Long id) {
        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Estoque não encontrado com ID: " + id));
        estoqueRepository.delete(estoque);
    }
}