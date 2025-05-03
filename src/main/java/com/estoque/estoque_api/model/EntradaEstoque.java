package com.estoque.estoque_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity (name = "entradaestoque")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EntradaEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    public Produto produto;

    public Integer quantidade;

    public LocalDateTime entradaEstoque;
}
