package com.estoque.estoque_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity(name = "saidaestoque")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SaidaEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    public Produto produtoId;

    public Integer quantidade;

    public LocalDateTime dataSaida;

}
