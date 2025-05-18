package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.dto.SaidaEstoqueDTO;
import com.estoque.estoque_api.model.SaidaEstoque;
import com.estoque.estoque_api.service.SaidaEstoqueService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saidaestoque")
public class SaidaEstoqueController {

    @Autowired
    SaidaEstoqueService saidaEstoqueService;

    @GetMapping("/{id}")
    public ResponseEntity<SaidaEstoqueDTO> findById(@PathVariable Long id){
           SaidaEstoqueDTO saidaEstoqueDTO = saidaEstoqueService.findById(id);

           return ResponseEntity.ok(saidaEstoqueDTO);
    }

    @PostMapping
    public ResponseEntity<SaidaEstoqueDTO> registrarSaida(@Valid @RequestBody SaidaEstoqueDTO saidaEstoqueDTO){
        SaidaEstoqueDTO saidaDTO =  saidaEstoqueService.registrarSaida(saidaEstoqueDTO);

        return ResponseEntity.ok(saidaEstoqueDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSaida(@PathVariable Long id){
        saidaEstoqueService.deletarSaida(id);

        return ResponseEntity.noContent().build();
    }

}
