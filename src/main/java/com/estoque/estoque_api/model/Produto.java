package com.estoque.estoque_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="produto")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "estoque_id")
    private Estoque estoque;

    private Boolean preco;
}
