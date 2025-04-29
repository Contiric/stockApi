package com.estoque.estoque_api.mapper;

import com.estoque.estoque_api.dto.ProdutoDTO;
import com.estoque.estoque_api.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public ProdutoDTO toDto(Produto produto){

        ProdutoDTO produtoDTO = new ProdutoDTO();

        produtoDTO.setId(produto.getId());
        produtoDTO.setCategoria(produto.getCategoria());
        produtoDTO.setNome(produtoDTO.getNome());
        produtoDTO.setDescricao(produtoDTO.getDescricao());

        return produtoDTO;
    }

    public Produto toEntity(ProdutoDTO produtoDTO){

        Produto produto = new Produto();

        produto.setId(produtoDTO.getId());
        produto.setCategoria(produtoDTO.getCategoria());
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());

        return produto;
    }

}
