package com.estoque.estoque_api.service;

import com.estoque.estoque_api.dto.ProdutoDTO;
import com.estoque.estoque_api.exception.BusinessException;
import com.estoque.estoque_api.mapper.ProdutoMapper;
import com.estoque.estoque_api.model.Produto;
import com.estoque.estoque_api.repository.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

        @Autowired
        ProdutoRepository produtoRepository;

        @Autowired
        ProdutoMapper produtoMapper;

        private static final Logger logger = LoggerFactory.getLogger(ProdutoService.class);

        public ProdutoDTO criarProduto(ProdutoDTO produtoDTO) {
            logger.info("Iniciando criação do produto: {}", produtoDTO.getNome());

            Produto produto = produtoMapper.toEntity(produtoDTO);
            Produto produtoSalvo = produtoRepository.save(produto);

            logger.info("Produto criado com sucesso: ID {}", produtoSalvo.getId());
            return produtoMapper.toDto(produtoSalvo);
        }

        public List<ProdutoDTO> listarProdutos() {
            logger.info("Listando produtos existentes");

            List<Produto> produtos = produtoRepository.findAll();
            if (produtos.isEmpty()){
                logger.warn("Nenhum produto encontrado");
                throw new BusinessException(("Nenhum produto encontrado"));
            }
            return produtos
                    .stream()
                    .map(produtoMapper::toDto)
                    .collect(Collectors.toList());
        }

        public ProdutoDTO buscarPorId(Long id) {
            Produto produto = produtoRepository.findById(id)
                    .orElseThrow(() -> {
                        logger.warn("Produto não encontrado para ID: {}", id);
                        return new BusinessException("Produto não encontrado com ID: " + id);
                    });

            logger.info("Produto encontrado: {}", produto);
            return produtoMapper.toDto(produto);
        }

        public ProdutoDTO atualizar(Long id, ProdutoDTO produtoAtualizado){
            logger.info("Atualizar produto com id:" + id);

            Produto produto = produtoRepository.findById(id)
                    .orElseThrow(() -> {
                        logger.warn("Id não encontrado:" + id);
                        return new BusinessException("Produto não encontrada com id: " + id);
                    });

            produto.setNome(produtoAtualizado.getNome());
            produto.setDescricao(produtoAtualizado.getDescricao());

            Produto salvo = produtoRepository.save(produto);

            logger.info("Produto atualizado:" + id);

            return produtoMapper.toDto(salvo);
        }

        public void deletarProduto(Long id) {
            logger.info("Deletando Produto id:" + id);

            Produto produto = produtoRepository.findById(id)
                    .orElseThrow(() -> {
                        logger.warn("Produto não encontrado para ID: {}", id);
                        return new BusinessException("Produto não encontrado com ID: " + id);
                    });
            produtoRepository.delete(produto);
        }

    }
