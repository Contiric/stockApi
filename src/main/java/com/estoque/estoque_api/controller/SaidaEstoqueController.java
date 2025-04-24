package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.model.SaidaEstoque;
import com.estoque.estoque_api.service.SaidaEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/saidaestoque")
public class SaidaEstoqueController {

    @Autowired
    SaidaEstoqueService saidaEstoqueService;

    @GetMapping("/{id}")
    public Optional<SaidaEstoque> findById(Long id){
        return saidaEstoqueService.findById(id);
    }

    @PostMapping
    public SaidaEstoque registrarSaida(@RequestBody SaidaEstoque saidaEstoque){
        return saidaEstoqueService.registrarSaida(saidaEstoque);
    }

    @DeleteMapping
    public void deletarSaida(@PathVariable Long id){
        saidaEstoqueService.deletarSaida(id);
    }

}
