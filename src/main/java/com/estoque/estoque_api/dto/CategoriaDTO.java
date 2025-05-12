package com.estoque.estoque_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoriaDTO {

    private Long id;

    @NotBlank(message = "O nome da categoria é obrigatório")
    @Size(min = 2, max = 50, message = "O nome da categoria deve ter entre 2 e 50 caracteres")
    private String nome;

    @NotBlank
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
