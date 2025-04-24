package com.estoque.estoque_api.repository;

import com.estoque.estoque_api.model.SaidaEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaidaEstoqueRepository extends JpaRepository<SaidaEstoque, Long> {
}
