package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.dto.CategoriaDTO;
import com.estoque.estoque_api.model.Categoria;
import com.estoque.estoque_api.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable Long id) {
        CategoriaDTO dto = categoriaService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<CategoriaDTO> listarCategorias() {
        List<CategoriaDTO> categoriaDTOS = categoriaService.listarCategorias();
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> criarCategoria(@Valid @RequestBody Categoria categoria) {
        CategoriaDTO categoriaDTO = categoriaService.criarCategoria(categoria);
        return ResponseEntity.ok(categoriaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> atualizar(@PathVariable Long id, @RequestBody CategoriaDTO categoriaAtualizado) {
        CategoriaDTO atualizar = categoriaService.atualizar(id, categoriaAtualizado);
        return ResponseEntity.ok(atualizar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerCategoria(@PathVariable Long id) {
         categoriaService.removerCategoria(id);
         return ResponseEntity.noContent().build();
    }
}
