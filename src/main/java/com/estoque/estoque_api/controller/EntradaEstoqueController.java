package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.model.EntradaEstoque;
import com.estoque.estoque_api.service.EntradaEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entradaestoque")
public class EntradaEstoqueController {

    @Autowired
    EntradaEstoqueService entradaEstoqueService;

    @GetMapping("/{id}")
    public Optional<EntradaEstoque> findById(@PathVariable Long id) {
        return entradaEstoqueService.findbyId(id);
    }

    @GetMapping
    public List<EntradaEstoque> listarEntradas(){
        return entradaEstoqueService.listarEntradas();
    }

    @PostMapping
    public EntradaEstoque registrarEntrada(@RequestBody EntradaEstoque entradaEstoque){
        return entradaEstoqueService.registrarEntrada(entradaEstoque);
    }

    @DeleteMapping
    public void deletarEntrada(@RequestBody EntradaEstoque entradaEstoque){
        entradaEstoqueService.deletarEntrada(entradaEstoque);
    }
}
