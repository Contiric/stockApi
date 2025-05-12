package com.estoque.estoque_api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EntradaEstoqueDTO {

    public Long id;

    @NotNull(message = "O ID do produto é obrigatório")
    public Long produtoId;

    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 1, message = "A quantidade mínima deve ser 1")
    public Integer quantidade;

    @NotNull(message = "A data de entrada é obrigatória")
    public LocalDateTime entradaEstoque;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade (Integer quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDateTime getEntradaEstoque() {
        return entradaEstoque;
    }

    public void setEntradaEstoque (LocalDateTime entradaEstoque) {
        this.entradaEstoque = entradaEstoque;
    }
}
