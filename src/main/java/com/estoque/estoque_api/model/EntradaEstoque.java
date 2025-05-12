package com.estoque.estoque_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity (name = "entradaestoque")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntradaEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    public Produto produto;

    public Integer quantidade;

    public LocalDateTime entradaEstoque;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDateTime getEntradaEstoque() {
        return entradaEstoque;
    }

    public void setEntradaEstoque(LocalDateTime entradaEstoque) {
        this.entradaEstoque = entradaEstoque;
    }
}
