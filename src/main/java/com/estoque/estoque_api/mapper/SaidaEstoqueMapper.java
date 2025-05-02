package com.estoque.estoque_api.mapper;

import com.estoque.estoque_api.dto.SaidaEstoqueDTO;
import com.estoque.estoque_api.model.SaidaEstoque;

public class SaidaEstoqueMapper {

    public SaidaEstoqueDTO toDTO(SaidaEstoque saidaEstoque){
        SaidaEstoqueDTO saidaEstoqueDTO = new SaidaEstoqueDTO();

        saidaEstoqueDTO.setId(saidaEstoque.getId());
        saidaEstoqueDTO.setQuantidade(saidaEstoque.getQuantidade());
        saidaEstoqueDTO.setDataSaida(saidaEstoque.getDataSaida());

        return saidaEstoqueDTO;
    }

    public SaidaEstoque toEntity(SaidaEstoqueDTO saidaEstoqueDTO){
        SaidaEstoque saidaEstoque = new SaidaEstoque();

        saidaEstoque.setId(saidaEstoqueDTO.getId());
        saidaEstoque.setQuantidade(saidaEstoqueDTO.getQuantidade());
        saidaEstoque.setDataSaida(saidaEstoqueDTO.getDataSaida());

        return saidaEstoque;
    }
}
