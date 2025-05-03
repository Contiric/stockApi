package com.estoque.estoque_api.service;

import com.estoque.estoque_api.dto.SaidaEstoqueDTO;
import com.estoque.estoque_api.exception.BusinessException;
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
    SaidaEstoqueRepository saidaEstoqueRepository;

    @Autowired
    SaidaEstoqueMapper saidaEstoqueMapper;

    public SaidaEstoqueDTO findById(Long id){
        SaidaEstoque saidaEstoque = saidaEstoqueRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Não foi encontrado o id da saída:" + id));

        return saidaEstoqueMapper.toDTO(saidaEstoque);
    }

    public List<SaidaEstoqueDTO> findAll(){
        return saidaEstoqueRepository.findAll()
                .stream().map(saidaEstoqueMapper::toDTO)
                .collect(Collectors.toList());
    }

    public SaidaEstoqueDTO registrarSaida(SaidaEstoqueDTO saidaDTO){
        SaidaEstoque saida = saidaEstoqueMapper.toEntity(saidaDTO);
        SaidaEstoque novaSaida = saidaEstoqueRepository.save(saida);
        return saidaEstoqueMapper.toDTO(novaSaida);
    }

    public void deletarSaida(Long id){
        SaidaEstoque saidaEstoque = saidaEstoqueRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Saída não encontrada com ID: " + id));
        saidaEstoqueRepository.delete(saidaEstoque);
    }

}
