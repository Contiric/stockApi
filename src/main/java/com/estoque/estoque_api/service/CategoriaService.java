package com.estoque.estoque_api.service;

import com.estoque.estoque_api.dto.CategoriaDTO;
import com.estoque.estoque_api.exception.BusinessException;
import com.estoque.estoque_api.mapper.CategoriaMapper;
import com.estoque.estoque_api.model.Categoria;
import com.estoque.estoque_api.repository.CategoriaRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    CategoriaMapper categoriaMapper;

    private static final Logger logger = LoggerFactory.getLogger(CategoriaService.class);

    public CategoriaDTO findById(Long id) {
        logger.info("Iniciando criação da categoria: {}", id);

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Categoria não encontrado para ID: {}", id);
                    return new BusinessException("Categoria não encontrado com ID: " + id);
                });

        logger.info("Categoria encontrado: {}", categoria);
        return categoriaMapper.toDto(categoria);
    }

    public List<CategoriaDTO> listarCategorias() {
        logger.info("Listando categorias existentes");

        List<Categoria> categorias = categoriaRepository.findAll();
        if (categorias.isEmpty()) {
            logger.warn("Nenhuma categoria encontrada");
            throw new BusinessException(("Nenhuma categoria encontrada"));
        }
        return categorias.stream()
                .map(categoriaMapper::toDto)
                .collect(Collectors.toList());
    }

    public CategoriaDTO criarCategoria(CategoriaDTO categoriaDTO) {
        logger.info("Iniciando criação da categoria: {}", categoriaDTO.getNome());

        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Categoria categoriaSalvo = categoriaRepository.save(categoria);

        logger.info("Categoria criada com sucesso: ID {}", categoriaSalvo.getId());
        return categoriaMapper.toDto(categoriaSalvo);
    }

    public CategoriaDTO atualizar(Long id, CategoriaDTO categoriaDTO) {
        logger.info("Atualizar categoria com id:" + id);

        Categoria categoria = categoriaRepository
                .findById(id)
                .orElseThrow(() -> {
                    logger.warn("Id não encontrado:" + id);
                    return new BusinessException("Categoria não encontrada com id: " + id);
                        });

        categoria.setDescricao(categoriaDTO.getDescricao());
        categoria.setNome(categoriaDTO.getNome());
        Categoria salvo = categoriaRepository.save(categoria);

        logger.info("Categoria atualizado:" + id);

        return categoriaMapper.toDto(salvo);
    }

    public void removerCategoria(Long id) {
        logger.info("Deletando categoria id:" + id);

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Categoria não encontrada para ID: {}", id);
                    return new BusinessException("Categoria não encontrada com ID: " + id);
                });
        categoriaRepository.delete(categoria);
    }
}





