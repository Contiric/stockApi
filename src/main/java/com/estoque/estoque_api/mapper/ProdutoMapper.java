package com.estoque.estoque_api.mapper;

import com.estoque.estoque_api.dto.ProdutoDTO;
import com.estoque.estoque_api.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public ProdutoDTO toDto(Produto produto){

        ProdutoDTO produtoDTO = new ProdutoDTO();

        produtoDTO.setId(produto.getId());
        produtoDTO.setNome(produto.getNome());
        produtoDTO.setDescricao(produto.getDescricao());
        produtoDTO.setPreco(produto.getPreco());

        return produtoDTO;
    }

    public Produto toEntity(ProdutoDTO produtoDTO){

        Produto produto = new Produto();

        produto.setId(produtoDTO.getId());
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPreco(produtoDTO.getPreco());

        return produto;
    }

}
