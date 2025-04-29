package com.estoque.estoque_api.service;

import com.estoque.estoque_api.dto.ProdutoDTO;
import com.estoque.estoque_api.mapper.ProdutoMapper;
import com.estoque.estoque_api.model.Produto;
import com.estoque.estoque_api.repository.ProdutoRepository;
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

        public ProdutoDTO criarProduto(ProdutoDTO produtoDTO) {
            Produto produto = produtoMapper.toEntity(produtoDTO);
            Produto produtoSalvo = produtoRepository.save(produto);
            return produtoMapper.toDto(produtoSalvo);
        }

        public List<ProdutoDTO> listarProdutos() {
            List<Produto> produtos = produtoRepository.findAll();
            return produtos
                    .stream()
                    .map(produtoMapper::toDto)
                    .collect(Collectors.toList());
        }

        public ProdutoDTO buscarId(Long id) {
            Produto produto = produtoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));
            return produtoMapper.toDto(produto);
        }

        public ProdutoDTO atualizar(Long id, ProdutoDTO produtoAtualizado){
            Produto produto = produtoRepository.findById(id)
                         .orElseThrow(()-> new RuntimeException("Produto não encontrado com id: " + id));
            produto.setNome(produtoAtualizado.getNome());
            produto.setDescricao(produtoAtualizado.getDescricao());
            produto.setCategoria(produtoAtualizado.getCategoria());

            Produto salvo = produtoRepository.save(produto);

            return produtoMapper.toDto(salvo);
        }

        public void deletarProduto(Long id) {
            produtoRepository.deleteById(id);
        }

    }
