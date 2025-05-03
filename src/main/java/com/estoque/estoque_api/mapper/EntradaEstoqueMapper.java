package com.estoque.estoque_api.mapper;

import com.estoque.estoque_api.dto.EntradaEstoqueDTO;
import com.estoque.estoque_api.model.EntradaEstoque;
import org.springframework.stereotype.Component;

@Component
public class EntradaEstoqueMapper {

    public EntradaEstoqueDTO toDTO(EntradaEstoque entradaEstoque){
        EntradaEstoqueDTO entradaEstoqueDTO = new EntradaEstoqueDTO();

        entradaEstoqueDTO.setId(entradaEstoque.getId());
        entradaEstoqueDTO.setQuantidade(entradaEstoque.getQuantidade());
        entradaEstoqueDTO.setEntradaEstoque(entradaEstoque.getEntradaEstoque());

        return entradaEstoqueDTO;
    }

    public EntradaEstoque toEntity(EntradaEstoqueDTO entradaEstoqueDTO){
        EntradaEstoque entradaEstoque = new EntradaEstoque();

        entradaEstoque.setId(entradaEstoqueDTO.getId());
        entradaEstoque.setQuantidade(entradaEstoqueDTO.getQuantidade());
        entradaEstoque.setEntradaEstoque(entradaEstoqueDTO.getEntradaEstoque());

        return entradaEstoque;
    }
}
