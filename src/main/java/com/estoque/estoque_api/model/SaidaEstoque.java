package com.estoque.estoque_api.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

public class SaidaEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    public Produto produto_id;

    public Integer quantidade;

    public LocalDateTime data_saida;

}
