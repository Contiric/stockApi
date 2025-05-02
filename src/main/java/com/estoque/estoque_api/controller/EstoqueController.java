package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.dto.EstoqueDTO;
import com.estoque.estoque_api.model.Estoque;
import com.estoque.estoque_api.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
    
    @Autowired
    EstoqueService estoqueService;

    @GetMapping("/{id}")
    public ResponseEntity<EstoqueDTO> findById(@PathVariable Long id) {
            EstoqueDTO byId = estoqueService.findById(id);
            return ResponseEntity.ok(byId);
    }

    @GetMapping
    public ResponseEntity<List<EstoqueDTO>> listarEstoques() {
        List<EstoqueDTO> estoqueDTOS = estoqueService.listarEstoque();
        return ResponseEntity.ok(estoqueDTOS);
    }

    @PostMapping
    public ResponseEntity<EstoqueDTO> criarEstoque(@RequestBody Estoque Estoque) {
        EstoqueDTO estoqueDTO = estoqueService.criarEstoque(Estoque);

        return ResponseEntity.ok(estoqueDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstoqueDTO> atualizar(@PathVariable Long id, @RequestBody EstoqueDTO estoqueAtualizado) {
        EstoqueDTO atualizado = estoqueService.atualizar(id, estoqueAtualizado);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerEstoque(@PathVariable Long id) {
        estoqueService.removerEstoque(id);
        return ResponseEntity.noContent().build();
    }
}

