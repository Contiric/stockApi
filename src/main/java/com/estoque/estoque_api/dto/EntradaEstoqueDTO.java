package com.estoque.estoque_api.dto;

import com.estoque.estoque_api.model.Produto;

import java.time.LocalDateTime;

public class EntradaEstoqueDTO {

    public Long id;

    public Produto produto_id;

    public Integer quantidade;

    public LocalDateTime data_saida;
}
