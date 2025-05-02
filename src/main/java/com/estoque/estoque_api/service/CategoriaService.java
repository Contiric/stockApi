package com.estoque.estoque_api.service;

import com.estoque.estoque_api.dto.CategoriaDTO;
import com.estoque.estoque_api.mapper.CategoriaMapper;
import com.estoque.estoque_api.model.Categoria;
import com.estoque.estoque_api.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    CategoriaMapper categoriaMapper;

    public CategoriaDTO findById(Long id) {
        Categoria byId = categoriaRepository.findById(id)
                .orElseThrow(() ->new RuntimeException("Categoria não encontrada com o id:" + id));
        return categoriaMapper.toDto(byId);
    }

    public List<CategoriaDTO> listarCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
              return categorias.stream()
                .map(categoriaMapper ::toDto)
                .collect(Collectors.toList());

    }

    public CategoriaDTO criarCategoria(Categoria categoria) {
        Categoria categoria1 = categoriaRepository.save(categoria);
        return categoriaMapper.toDto(categoria1);
    }

    public CategoriaDTO atualizar(Long id, CategoriaDTO categoriaAtualizado) {

         Categoria categoria = categoriaRepository
                 .findById(id).orElseThrow(()-> new RuntimeException("Produto não encontrado com id: " + id));

                    categoria.setDescricao(categoriaAtualizado.getDescricao());
                    categoria.setNome(categoria.getNome());
        Categoria salvo = categoriaRepository.save(categoria);

        return categoriaMapper.toDto(salvo);
    }

    public void removerCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

}
