package com.estoque.estoque_api.mapper;

import com.estoque.estoque_api.dto.EstoqueDTO;
import com.estoque.estoque_api.model.Estoque;
import com.estoque.estoque_api.model.Produto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EstoqueMapper {

    public EstoqueDTO toDTO(Estoque estoque){
        EstoqueDTO estoqueDTO = new EstoqueDTO();

        estoqueDTO.setId(estoque.getId());
        estoqueDTO.setQuantidade(estoque.getQuantidade());
        List<Long> produtoIds = estoque.getProdutos().stream()
                .map(Produto::getId)
                .collect(Collectors.toList());

        estoqueDTO.setProdutoIds(produtoIds);

        return estoqueDTO;
    }

    public Estoque toEntity(EstoqueDTO estoqueDTO){
        Estoque estoque = new Estoque();

        estoque.setId(estoqueDTO.getId());
        estoque.setQuantidade(estoqueDTO.getQuantidade());

        List<Produto> produtos = estoqueDTO.getProdutoIds().stream().map(id -> {
            Produto produto = new Produto();
            produto.setId(id);
            produto.setEstoque(estoque);
            return produto;
        }).collect(Collectors.toList());

        estoque.setProdutos(produtos);

        return estoque;
    }
}
