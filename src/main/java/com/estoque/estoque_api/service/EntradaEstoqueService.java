package com.estoque.estoque_api.service;

import com.estoque.estoque_api.dto.EntradaEstoqueDTO;
import com.estoque.estoque_api.exception.BusinessException;
import com.estoque.estoque_api.mapper.EntradaEstoqueMapper;
import com.estoque.estoque_api.model.EntradaEstoque;
import com.estoque.estoque_api.repository.EntradaEstoqueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(EntradaEstoqueService.class);

    public EntradaEstoqueDTO findById(Long id){
        logger.info("Iniciando criação da Entrada de Estoque: {}", id);

        EntradaEstoque entradaEstoque = entradaEstoqueRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Entrada de Estoque não encontrado para ID: {}", id);
                return new BusinessException("Entrada de Estoque não encontrado com ID: " + id);
        });
        return entradaEstoqueMapper.toDTO(entradaEstoque);
    }

    public List<EntradaEstoqueDTO> listarEntradas(){
        logger.info("Listando Entradas de Estoque existentes");

        List<EntradaEstoque> entradaEstoques = entradaEstoqueRepository.findAll();
        if (entradaEstoques.isEmpty()){
            logger.warn("Nenhuma entrada encontrada");
            throw new BusinessException(("Nenhuma entrada encontrada"));
        }

        return entradaEstoques.stream().map(entradaEstoqueMapper::toDTO)
                .collect(Collectors.toList());
    }

    public EntradaEstoqueDTO registrarEntrada(EntradaEstoqueDTO entradaEstoqueDTO){
        logger.info("Iniciando criação da categoria: {}", entradaEstoqueDTO.getId());

        EntradaEstoque entradaEstoque = entradaEstoqueMapper.toEntity(entradaEstoqueDTO);
        EntradaEstoque entradaSalvo = entradaEstoqueRepository.save(entradaEstoque);

        logger.info("Entrada criada com sucesso: ID {}", entradaSalvo.getId());
        return entradaEstoqueMapper.toDTO(entradaSalvo);
    }

    public void deletarEntrada(Long id){
        logger.info("Deletando entrada id:" + id);

        EntradaEstoque entrada = entradaEstoqueRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Entrada não encontrada para ID: {}", id);
                    return new BusinessException("Entrada não encontrada com ID: " + id);
                });
        entradaEstoqueRepository.deleteById(id);
    }

}
