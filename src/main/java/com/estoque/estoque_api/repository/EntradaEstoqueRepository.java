package com.estoque.estoque_api.repository;

import com.estoque.estoque_api.model.EntradaEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntradaEstoqueRepository extends JpaRepository<EntradaEstoque, Long> {
}
