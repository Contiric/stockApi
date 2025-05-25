package com.estoque.estoque_api.service;

import com.estoque.estoque_api.dto.CategoriaDTO;
import com.estoque.estoque_api.exception.BusinessException;
import com.estoque.estoque_api.mapper.CategoriaMapper;
import com.estoque.estoque_api.model.Categoria;
import com.estoque.estoque_api.model.Produto;
import com.estoque.estoque_api.repository.CategoriaRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoriaServiceTeste {

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private CategoriaMapper categoriaMapper;

    @InjectMocks
    private CategoriaService categoriaService;

    private Categoria categoria;

    private CategoriaDTO categoriaDTO;

    private Produto produto;

    @BeforeEach
    void setup(){

        produto = new Produto();
        produto.setId(1L);
        produto.setNome("Camisa Preta");
        produto.setDescricao("Camisa Preta Gola V Básica");
        produto.setPreco(30.00);

        categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNome("Eletrônicos");
        categoria.setDescricao("Tvs e Monitores");
        categoria.setProdutos(List.of(produto));

        categoriaDTO = new CategoriaDTO();
        categoriaDTO.setId(1L);
        categoriaDTO.setNome("Eletrônicos");
        categoriaDTO.setDescricao("Tvs e Monitores");

    }

    @Test
    void buscarCategoriaPorIdComSucesso(){
        //Preparar
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(categoriaMapper.toDto(categoria)).thenReturn(categoriaDTO);

        //Ação
        CategoriaDTO idEncontrado = categoriaService.findById(1L);

        //Verificar
        assertNotNull(idEncontrado);
        assertEquals(1L, idEncontrado.getId());
        verify(categoriaRepository).findById(1L);
        verify(categoriaMapper).toDto(categoria);

    }

    @Test
    void lancarBusinessExceptionQuandoIdForNulo(){

        //Ação
        final BusinessException e = Assertions.assertThrows(BusinessException.class, () ->{
            categoriaService.findById(null);
        });

        //Verificar
        assertThat(e.getMessage(), Matchers.containsString("Categoria não encontrado com ID"));
        assertThat(e.getCause(), nullValue());

    }

    @Test
    void listarCategoriasComSucesso(){
        //Preparar
        when(categoriaRepository.findAll()).thenReturn(List.of(categoria));
        when(categoriaMapper.toDto(categoria)).thenReturn(categoriaDTO);

        //Ação
        List<CategoriaDTO> categoriaDTOS = categoriaService.listarCategorias();

        //Verificar
        assertNotNull(categoriaDTOS);
        assertEquals(categoriaDTOS.get(0).getId(), categoria.getId());
        verify(categoriaRepository).findAll();

    }

    @Test
    void deveLancarBusinessExceptionSeListaForVazia(){
        //Preparar
        when(categoriaRepository.findAll()).thenReturn(Collections.emptyList());

        //Ação
        final BusinessException e = assertThrows(BusinessException.class, () ->{
            categoriaService.listarCategorias();
        });

        //Verificar
        assertThat(e.getMessage(), is("Nenhuma Categoria Encontrada"));
        verify(categoriaRepository).findAll();
        verifyNoMoreInteractions(categoriaRepository);
    }

    @Test
    void criarCategoriaComSucesso(){
        //Preparar
        when(categoriaMapper.toEntity(categoriaDTO)).thenReturn(categoria);
        when(categoriaRepository.save(categoria)).thenReturn(categoria);
        when(categoriaMapper.toDto(categoria)).thenReturn(categoriaDTO);

        //Ação
        CategoriaDTO categoriaSalva = categoriaService.criarCategoria(categoriaDTO);

        //Preparar
        assertNotNull(categoriaSalva);
        assertEquals(1L, categoriaSalva.getId());
        verify(categoriaRepository).save(categoria);
    }

    @Test
    void atualizarCategoriaComSucesso(){
        //Preparar
        Categoria categoriaAtualizado = new Categoria();
        categoriaAtualizado.setNome("Eletrodomésticos");

        CategoriaDTO categoriaDTOAtualizado = new CategoriaDTO();
        categoriaDTOAtualizado.setNome("Eletrodomésticos");

        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoriaAtualizado));
        when(categoriaRepository.save(ArgumentMatchers.any(Categoria.class))).thenReturn(categoriaAtualizado);
        when(categoriaMapper.toDto(categoriaAtualizado)).thenReturn(categoriaDTOAtualizado);

        //Ação
        CategoriaDTO categoriaNova = categoriaService.atualizar(1L, categoriaDTOAtualizado);

        //Verificar
        assertEquals(categoriaNova.getNome(), categoriaDTOAtualizado.getNome());
        assertEquals(categoriaNova.getId(), categoriaDTOAtualizado.getId());
        verify(categoriaRepository).save(ArgumentMatchers.any(Categoria.class));

    }

    @Test
    void removerCategoriaComSucesso(){
        //Prepara
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));

        //Ação
        categoriaService.removerCategoria(1L);

        //Verifica
        verify(categoriaRepository).delete(categoria);

    }

}
