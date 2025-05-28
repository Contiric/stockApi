package com.estoque.estoque_api.service;

import com.estoque.estoque_api.dto.EstoqueDTO;
import com.estoque.estoque_api.exception.BusinessException;
import com.estoque.estoque_api.mapper.EstoqueMapper;
import com.estoque.estoque_api.model.Estoque;
import com.estoque.estoque_api.repository.EstoqueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public static final Logger logger = LoggerFactory.getLogger(EstoqueService.class);

    public EstoqueDTO findById(Long id) {
        logger.info("Iniciando criação do estoque: {}", id);

        Estoque estoque = estoqueRepository.findById(id)
                    .orElseThrow(() -> {
                        logger.info("Categoria não encontrado para ID: {}", id);
                        return new BusinessException("Estoque não encontrado com ID: " + id);
                    });

        return estoqueMapper.toDTO(estoque);
    }

    public List<EstoqueDTO> listarEstoque() {
        logger.info("Listando estoques existentes");

        List<Estoque> estoques = estoqueRepository.findAll();
        if (estoques.isEmpty()){
            logger.warn("Nenhum estoque encontrado");
            throw new BusinessException(("Nenhum estoque encontrado"));
        }
        return estoques.stream()
                .map(estoqueMapper::toDTO)
                .collect(Collectors.toList());
    }

    public EstoqueDTO criarEstoque(EstoqueDTO estoqueDTO) {
        logger.info("Iniciando criação do estoque: {}", estoqueDTO.getId());

        Estoque estoque = estoqueMapper.toEntity(estoqueDTO);
        Estoque newEstoque = estoqueRepository.save(estoque);

        return estoqueMapper.toDTO(newEstoque);
    }

    public EstoqueDTO atualizar(Long id, EstoqueDTO estoqueAtualizado) {
        logger.info("Atualizar estoque com id:" + id);

        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Id não encontrado:" + id);
                    return new BusinessException("Estoque não encontrada com id: " + id);
                });

        estoque.setQuantidade(estoqueAtualizado.getQuantidade());

        Estoque newEstoque = estoqueRepository.save(estoque);

        logger.info("Estoque atualizado:" + id);

        return estoqueMapper.toDTO(newEstoque);
    }

    public void removerEstoque(Long id) {
        logger.info("Deletando estoque id:" + id);

        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Estoque não encontrado para ID: {}", id);
                    return new BusinessException("Estoque não encontrado com ID: " + id);
                });
        estoqueRepository.delete(estoque);
    }
}