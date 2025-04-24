package com.estoque.estoque_api.service;

import com.estoque.estoque_api.model.SaidaEstoque;
import com.estoque.estoque_api.repository.SaidaEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaidaEstoqueService {

    @Autowired
    SaidaEstoqueRepository estoqueRepository;

    public Optional<SaidaEstoque> findById(Long id){
        return estoqueRepository.findById(id);
    }

    public List<SaidaEstoque> findAll(){
        return estoqueRepository.findAll();
    }

    public SaidaEstoque registrarSaida(SaidaEstoque saidaEstoque){
        return estoqueRepository.save(saidaEstoque);
    }

    public void deletarSaida(Long id){
        estoqueRepository.deleteById(id);
    }

}
