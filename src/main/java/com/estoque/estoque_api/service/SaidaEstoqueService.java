package com.estoque.estoque_api.service;

import com.estoque.estoque_api.dto.SaidaEstoqueDTO;
import com.estoque.estoque_api.exception.BusinessException;
import com.estoque.estoque_api.mapper.SaidaEstoqueMapper;
import com.estoque.estoque_api.model.Categoria;
import com.estoque.estoque_api.model.SaidaEstoque;
import com.estoque.estoque_api.repository.SaidaEstoqueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public static final Logger logger = LoggerFactory.getLogger(SaidaEstoqueService.class);

    public SaidaEstoqueDTO findById(Long id){
        logger.info("Iniciando criação da saída: {}", id);

        SaidaEstoque saidaEstoque = saidaEstoqueRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Saída não encontrado com ID: {}", id);
                    return new BusinessException("Saída não encontrado com ID: " + id);
                });

        logger.info("Saída encontrado: {}", saidaEstoque);
        return saidaEstoqueMapper.toDTO(saidaEstoque);
    }

    public List<SaidaEstoqueDTO> findAll(){
        logger.info("Listando categorias existentes");

        List<SaidaEstoque> saidaEstoques = saidaEstoqueRepository.findAll();
        if (saidaEstoques.isEmpty()) {
            logger.warn("Nenhuma saída encontrada");
            throw new BusinessException(("Nenhuma saída encontrada"));
        }

        return saidaEstoques.stream()
                .map(saidaEstoqueMapper::toDTO)
                .collect(Collectors.toList());
    }

    public SaidaEstoqueDTO registrarSaida(SaidaEstoqueDTO saidaDTO){
        logger.info("Iniciando criação da saída: {}", saidaDTO.getId());


        SaidaEstoque saida = saidaEstoqueMapper.toEntity(saidaDTO);
        SaidaEstoque novaSaida = saidaEstoqueRepository.save(saida);

        logger.info("Saída criada com sucesso: ID {}", novaSaida.getId());
        return saidaEstoqueMapper.toDTO(novaSaida);
    }

    public void deletarSaida(Long id){
        logger.info("Deletando categoria id:" + id);


        SaidaEstoque saidaEstoque = saidaEstoqueRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Saída não encontrada para ID: {}", id);
                    return new BusinessException("Saída não encontrada com ID: " + id);
                });
        saidaEstoqueRepository.delete(saidaEstoque);
    }

}
