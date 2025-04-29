package com.estoque.estoque_api.dto;

import com.estoque.estoque_api.model.Categoria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO {

    private Long id;

    private String nome;

    private String descricao;

    private Categoria categoria;

    private Boolean preco;
}
