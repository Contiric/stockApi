package com.estoque.estoque_api.dto;

import com.estoque.estoque_api.model.Produto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EntradaEstoqueDTO {

    public Long id;

    public Produto produto_id;

    public Integer quantidade;

    public LocalDateTime entradaEstoque;
}
