package com.estoque.estoque_api.service;

import com.estoque.estoque_api.dto.EntradaEstoqueDTO;
import com.estoque.estoque_api.exception.BusinessException;
import com.estoque.estoque_api.mapper.EntradaEstoqueMapper;
import com.estoque.estoque_api.model.EntradaEstoque;
import com.estoque.estoque_api.repository.EntradaEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntradaEstoqueService {

    @Autowired
    EntradaEstoqueRepository entradaEstoqueRepository;

    @Autowired
    EntradaEstoqueMapper entradaEstoqueMapper;

    public EntradaEstoqueDTO findById(Long id){
        EntradaEstoque entradaEstoque = entradaEstoqueRepository.findById(id)
                .orElseThrow(()-> new BusinessException("Id de entrada não encontrado:" + id));

        return entradaEstoqueMapper.toDTO(entradaEstoque);
    }

    public List<EntradaEstoqueDTO> listarEntradas(){
        List<EntradaEstoque> all = entradaEstoqueRepository.findAll();

        return all.stream().map(entradaEstoqueMapper::toDTO)
                .collect(Collectors.toList());
    }

    public EntradaEstoqueDTO registrarEntrada(EntradaEstoque entradaEstoque){
        EntradaEstoque entrada = entradaEstoqueRepository.save(entradaEstoque);
        return entradaEstoqueMapper.toDTO(entrada);
    }

    public void deletarEntrada(Long id){
        EntradaEstoque entrada = entradaEstoqueRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Entrada de estoque não encontrada com o id: " + id));
        entradaEstoqueRepository.delete(entrada);
    }

}
