package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.dto.SaidaEstoqueDTO;
import com.estoque.estoque_api.model.SaidaEstoque;
import com.estoque.estoque_api.service.SaidaEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saidaestoque")
public class SaidaEstoqueController {

    @Autowired
    SaidaEstoqueService saidaEstoqueService;

    @GetMapping("/{id}")
    public ResponseEntity<SaidaEstoqueDTO> findById(Long id){
           SaidaEstoqueDTO saidaEstoqueDTO = saidaEstoqueService.findById(id);

           return ResponseEntity.ok(saidaEstoqueDTO);
    }

    @PostMapping
    public ResponseEntity<SaidaEstoqueDTO> registrarSaida(@RequestBody SaidaEstoque saidaEstoque){
        SaidaEstoqueDTO saidaEstoqueDTO =  saidaEstoqueService.registrarSaida(saidaEstoque);

        return ResponseEntity.ok(saidaEstoqueDTO);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarSaida(@PathVariable Long id){
        saidaEstoqueService.deletarSaida(id);

        return ResponseEntity.noContent().build();
    }

}
