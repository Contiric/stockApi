package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.service.EntradaEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/entradaestoque")
public class EntradaEstoqueController {

    @Autowired
    EntradaEstoqueService entradaEstoqueService;

    


}
