package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.model.Categoria;
import com.estoque.estoque_api.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/{id}")
    public Optional<Categoria> findById(@PathVariable Long id) {
        return categoriaService.findById(id);
    }

    @GetMapping
    public List<Categoria> listarCategorias() {
        return categoriaService.listarCategorias();
    }

    @PostMapping
    public Categoria criarCategoria(@RequestBody Categoria categoria) {
        return categoriaService.criarCategoria(categoria);
    }

    @PutMapping("/{id}")
    public Categoria atualizar(@PathVariable Long id, @RequestBody Categoria categoriaAtualizado) {
        return categoriaService.findById(id)
                .map(categoria ->  {
                    categoria.setDescricao(categoriaAtualizado.getDescricao());
                    categoria.setNome(categoria.getNome());
                    return categoriaService.criarCategoria(categoria);
                }).orElseThrow();

    }

    @DeleteMapping("/{id}")
    public void removerCategoria(@PathVariable Long id) {
        categoriaService.removerCategoria(id);
    }
}
