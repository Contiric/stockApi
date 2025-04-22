package com.estoque.estoque_api.service;

import com.estoque.estoque_api.model.Categoria;
import com.estoque.estoque_api.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public Optional<Categoria> findById(Long id) {
        return categoriaRepository.findById(id);
    }

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria criarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria atualizar(Long id, Categoria categoriaAtualizado) {
        return categoriaRepository.findById(id)
                .map(categoria ->  {
                    categoria.setDescricao(categoriaAtualizado.getDescricao());
                    categoria.setNome(categoria.getNome());
                    return categoriaRepository.save(categoria);
                }).orElseThrow();

    }

    public void removerCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

}
