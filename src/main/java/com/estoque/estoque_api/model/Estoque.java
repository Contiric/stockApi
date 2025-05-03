package com.estoque.estoque_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="estoque")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToMany(mappedBy = "estoque")
    public List<Produto> produtos;

    public Integer quantidade;
}
