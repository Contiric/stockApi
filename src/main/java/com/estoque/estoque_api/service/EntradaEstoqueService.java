package com.estoque.estoque_api.service;

import com.estoque.estoque_api.model.EntradaEstoque;
import com.estoque.estoque_api.repository.EntradaEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntradaEstoqueService {

    @Autowired
    EntradaEstoqueRepository entradaEstoqueRepository;

    public Optional<EntradaEstoque> findbyId(Long id){
        return entradaEstoqueRepository.findById(id);
    }

    public List<EntradaEstoque> listarEntradas(){
        return entradaEstoqueRepository.findAll();
    }

    public EntradaEstoque registrarEntrada(EntradaEstoque entradaEstoque){
        return entradaEstoqueRepository.save(entradaEstoque);
    }

    public void deletarEntrada(EntradaEstoque entradaEstoque){
        entradaEstoqueRepository.delete(entradaEstoque);
    }

}
