package com.estoque.estoque_api.dto;

import com.estoque.estoque_api.model.Produto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstoqueDTO {

    public Long id;

    public Produto produto_id;

    public int quantidade;
}
