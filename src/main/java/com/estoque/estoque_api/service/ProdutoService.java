package com.estoque.estoque_api.service;

import com.estoque.estoque_api.model.Produto;
import com.estoque.estoque_api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

        @Autowired
        ProdutoRepository produtoRepository;

        public Produto criarProduto(Produto produto) {
        return produtoRepository.save(produto);
        }

        public List<Produto> listarProdutos() {
            return produtoRepository.findAll();
        }

        public Optional<Produto> buscarId(Long id) {
            return produtoRepository.findById(id);
        }

        public Produto atualizar(Long id, Produto produtoAtualizado){
            return produtoRepository.findById(id)
                    .map(produto -> {
                        produto.setNome(produtoAtualizado.getNome());
                        produto.setDescricao(produtoAtualizado.getDescricao());
                        produto.setCategoria(produtoAtualizado.getCategoria());
                        return produtoRepository.save(produto);
                    }).orElseThrow();

        }

        public void deletarProduto(Long id) {
            produtoRepository.deleteById(id);
        }

    }
