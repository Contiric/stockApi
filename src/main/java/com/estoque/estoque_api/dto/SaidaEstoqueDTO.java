package com.estoque.estoque_api.dto;

import com.estoque.estoque_api.model.Produto;

import java.util.Date;

public class SaidaEstoqueDTO {

    public Long id;

    public Produto produto_id;

    public int quantidade;

    public Date data_saida;

}
