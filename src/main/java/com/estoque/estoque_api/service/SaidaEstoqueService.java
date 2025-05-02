package com.estoque.estoque_api.service;

import com.estoque.estoque_api.dto.SaidaEstoqueDTO;
import com.estoque.estoque_api.mapper.SaidaEstoqueMapper;
import com.estoque.estoque_api.model.SaidaEstoque;
import com.estoque.estoque_api.repository.SaidaEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaidaEstoqueService {

    @Autowired
    SaidaEstoqueRepository estoqueRepository;

    @Autowired
    SaidaEstoqueMapper saidaEstoqueMapper;

    public SaidaEstoqueDTO findById(Long id){
        SaidaEstoque saidaEstoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi encontrado o id da saída:" + id));

        return saidaEstoqueMapper.toDTO(saidaEstoque);
    }

    public List<SaidaEstoqueDTO> findAll(){
        return estoqueRepository.findAll()
                .stream().map(saidaEstoqueMapper::toDTO)
                .collect(Collectors.toList());
    }

    public SaidaEstoqueDTO registrarSaida(SaidaEstoque saidaEstoque){
        SaidaEstoque novaSaida = estoqueRepository.save(saidaEstoque);

        return saidaEstoqueMapper.toDTO(novaSaida);
    }

    public void deletarSaida(Long id){
        estoqueRepository.deleteById(id);
    }

}
