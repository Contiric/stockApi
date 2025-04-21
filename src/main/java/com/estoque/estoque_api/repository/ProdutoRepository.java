package com.estoque.estoque_api.repository;

import com.estoque.estoque_api.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
