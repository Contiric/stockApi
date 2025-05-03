package com.estoque.estoque_api.mapper;

import com.estoque.estoque_api.dto.EstoqueDTO;
import com.estoque.estoque_api.model.Estoque;
import org.springframework.stereotype.Component;

@Component
public class EstoqueMapper {

    public EstoqueDTO toDTO(Estoque estoque){
        EstoqueDTO estoqueDTO = new EstoqueDTO();

        estoqueDTO.setId(estoque.getId());
        estoqueDTO.setQuantidade(estoque.getQuantidade());

        return estoqueDTO;
    }

    public Estoque toEntity(EstoqueDTO estoqueDTO){
        Estoque estoque = new Estoque();

        estoque.setId(estoqueDTO.getId());
        estoque.setQuantidade(estoqueDTO.getQuantidade());

        return estoque;
    }
}
