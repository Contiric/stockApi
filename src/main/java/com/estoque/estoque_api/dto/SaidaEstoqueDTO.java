package com.estoque.estoque_api.dto;

import com.estoque.estoque_api.model.Produto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
@Getter
@Setter
public class SaidaEstoqueDTO {

    public Long id;

    public Produto produto_id;

    public int quantidade;

    public LocalDateTime dataSaida;

}
