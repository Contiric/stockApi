package com.estoque.estoque_api.repository;

import com.estoque.estoque_api.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
}