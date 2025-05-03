package com.estoque.estoque_api.mapper;

import com.estoque.estoque_api.dto.CategoriaDTO;
import com.estoque.estoque_api.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public CategoriaDTO toDto(Categoria categoria){
        CategoriaDTO categoriaDTO = new CategoriaDTO();

        categoriaDTO.setId(categoria.getId());
        categoriaDTO.setDescricao(categoria.getDescricao());
        categoriaDTO.setNome(categoria.getNome());

        return categoriaDTO;
    }

    public Categoria toEntity(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();

        categoria.setId(categoriaDTO.getId());
        categoria.setDescricao(categoriaDTO.getDescricao());
        categoria.setNome(categoriaDTO.getNome());

        return categoria;
    }
}
