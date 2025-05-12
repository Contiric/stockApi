package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.dto.EntradaEstoqueDTO;
import com.estoque.estoque_api.model.EntradaEstoque;
import com.estoque.estoque_api.service.EntradaEstoqueService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entradaestoque")
public class EntradaEstoqueController {

    @Autowired
    EntradaEstoqueService entradaEstoqueService;

    @GetMapping("/{id}")
    public ResponseEntity<EntradaEstoqueDTO> findById(@PathVariable Long id) {
        EntradaEstoqueDTO entradaEstoqueDTO = entradaEstoqueService.findById(id);
        return ResponseEntity.ok(entradaEstoqueDTO);
    }

    @GetMapping
    public ResponseEntity<List<EntradaEstoqueDTO>>  listarEntradas(){
        List<EntradaEstoqueDTO> entradaEstoqueDTOS = entradaEstoqueService.listarEntradas();

        return ResponseEntity.ok(entradaEstoqueDTOS);
    }

    @PostMapping
    public ResponseEntity<EntradaEstoqueDTO> registrarEntrada(@Valid @RequestBody EntradaEstoqueDTO entradaEstoqueDTO){
        EntradaEstoqueDTO entrada = entradaEstoqueService.registrarEntrada(entradaEstoqueDTO);

        return ResponseEntity.ok(entradaEstoqueDTO);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarEntrada(@RequestBody Long id){
        entradaEstoqueService.deletarEntrada(id);

        return ResponseEntity.noContent().build();
    }
}
