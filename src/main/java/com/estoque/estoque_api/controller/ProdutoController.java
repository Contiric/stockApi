package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.dto.ProdutoDTO;
import com.estoque.estoque_api.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoDTO> criar(@Valid @RequestBody ProdutoDTO produtoDTO){
        ProdutoDTO novoProduto = produtoService.criarProduto(produtoDTO);
        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarProdutos() {
        List<ProdutoDTO> produtoDTOS = produtoService.listarProdutos();
        return ResponseEntity.ok(produtoDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarId(@PathVariable Long id) {
        ProdutoDTO produtoDTO = produtoService.buscarId(id);
        return ResponseEntity.ok(produtoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizar(@PathVariable Long id, @RequestBody ProdutoDTO produtoAtualizado){
        ProdutoDTO atualizar = produtoService.atualizar(id, produtoAtualizado);
        return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

}
