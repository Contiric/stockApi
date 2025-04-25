package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.model.Estoque;
import com.estoque.estoque_api.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
    
    @Autowired
    EstoqueService estoqueService;

    @GetMapping("/{id}")
    public Optional<Estoque> findById(@PathVariable Long id) {
        return estoqueService.findById(id);
    }

    @GetMapping
    public List<Estoque> listarEstoques() {
        return estoqueService.listarEstoque();
    }

    @PostMapping
    public Estoque criarEstoque(@RequestBody Estoque Estoque) {
        return estoqueService.criarEstoque(Estoque);
    }

    @PutMapping("/{id}")
    public Estoque atualizar(@PathVariable Long id, @RequestBody Estoque estoqueAtualizado) {
        return estoqueService.findById(id)
                .map(estoque ->  {
                    estoque.setQuantidade(estoqueAtualizado.getQuantidade());
                    estoque.setProduto_id(estoqueAtualizado.getProduto_id());
                    return estoqueService.criarEstoque(estoque);
                }).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void removerEstoque(@PathVariable Long id) {
        estoqueService.removerEstoque(id);
    }
}

