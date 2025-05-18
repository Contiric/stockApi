package com.estoque.estoque_api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EstoqueDTO {

    public Long id;

    @NotNull(message = "O id do produto não pode ser nulo")
    public List<Long> produtoIds;

    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 1, message = "A quantidade mínima deve ser 1")
    public int quantidade;

    public List<Long> getProdutoIds() {
        return produtoIds;
    }

    public void setProdutoIds(@NotNull List<Long> produtoIds) {
        this.produtoIds = produtoIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade (int quantidade) {
        this.quantidade = quantidade;
    }
}
