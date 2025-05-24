package com.estoque.estoque_api.service;

import com.estoque.estoque_api.dto.ProdutoDTO;
import com.estoque.estoque_api.exception.BusinessException;
import com.estoque.estoque_api.mapper.ProdutoMapper;
import com.estoque.estoque_api.model.Produto;
import com.estoque.estoque_api.repository.ProdutoRepository;
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

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {
    
    @Mock
    ProdutoMapper produtoMapper;
    
    @Mock
    ProdutoRepository produtoRepository;
    
    @InjectMocks
    ProdutoService produtoService;

    private ProdutoDTO produtoDTO;
    private Produto produto;

    @BeforeEach
    void setup(){
        produto = new Produto();
        produto.setId(1L);
        produto.setNome("Camisa Preta");
        produto.setDescricao("Camisa Preta Gola V Básica");
        produto.setPreco(30.00);

        produtoDTO = new ProdutoDTO();
        produtoDTO.setId(1L);
        produtoDTO.setNome("Camisa Preta");
        produtoDTO.setDescricao("Camisa Preta Gola V Básica");
        produtoDTO.setPreco(30.00);

    }

    @Test
    void criarProdutoComSucesso(){
      //Preparar
      when(produtoMapper.toEntity(produtoDTO)).thenReturn(produto);
      when(produtoRepository.save(produto)).thenReturn(produto);
      when(produtoMapper.toDto(produto)).thenReturn(produtoDTO);

      //Ação
      ProdutoDTO produtoResultado = produtoService.criarProduto(produtoDTO);

      //Verificar
      assertNotNull(produtoResultado);
      assertEquals(1L, produtoResultado.getId());
      verify(produtoRepository).save(produto);

    }

    @Test
    void listarProdutosComSucesso(){
        //Preparar
        when(produtoRepository.findAll()).thenReturn(List.of(produto));
        when(produtoMapper.toDto(produto)).thenReturn(produtoDTO);

        //Ação
        List<ProdutoDTO> produtoDTOS = produtoService.listarProdutos();

        assertNotNull(produtoDTOS);
        assertEquals(produtoDTOS.size(), produtoDTO.getId());
        assertEquals(produtoDTOS.get(0).getId(), produto.getId());
        verify(produtoRepository).findAll();

    }

    @Test
    void deveLancarBusinessExceptionComListaVazia(){
         //Preparar
         when(produtoRepository.findAll()).thenReturn(Collections.emptyList());

         //Ação
         final BusinessException e = assertThrows(BusinessException.class, () -> {
             produtoService.listarProdutos();
         });

        //Verificar
        assertThat(e.getMessage(), is("Nenhum produto encontrado"));
        verify(produtoRepository).findAll();
        verifyNoMoreInteractions(produtoRepository);

    }

    @Test
    void buscarPorIdComSucesso(){
        //Preparar
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        when(produtoMapper.toDto(produto)).thenReturn(produtoDTO);

        //Ação
        ProdutoDTO produtoEncontrado = produtoService.buscarPorId(1L);

        //Verifica
        assertNotNull(produtoEncontrado);
        assertEquals(1L, produtoEncontrado.getId());
        verify(produtoRepository).findById(1L);
        verify(produtoMapper).toDto(produto);

    }

    @Test
    void deveLancarBusinessExceptionSeProdutoNull(){
        //Preparar

        final BusinessException e = assertThrows(BusinessException.class, () ->{
            produtoService.buscarPorId(null);
        });

        //Verificar
        assertThat(e, notNullValue());
        assertThat(e.getMessage(), containsString("Produto não encontrado"));
        assertThat(e.getCause(), nullValue());
    }

    @Test
    void atualizarProdutoComSucesso(){
        //Preparar
        Produto produtoAtualizado = new Produto();
        produtoAtualizado.setPreco(35.00);

        ProdutoDTO produtoDTOAtualizado = new ProdutoDTO();
        produtoDTOAtualizado.setPreco(35.00);

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produtoAtualizado));
        when(produtoRepository.save(ArgumentMatchers.any(Produto.class))).thenReturn(produtoAtualizado);
        when(produtoMapper.toDto(produtoAtualizado)).thenReturn(produtoDTOAtualizado);

        //Ação
        ProdutoDTO atualizado = produtoService.atualizar(1L, produtoDTOAtualizado);

        //Verificar
        assertEquals(atualizado.getPreco(), produtoDTOAtualizado.getPreco());
        assertEquals(atualizado.getId(), atualizado.getId());
        verify(produtoRepository).save(ArgumentMatchers.any(Produto.class));

    }

    @Test
    void deletarProdutoComSucesso(){
        //Prepara
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        //Ação
        produtoService.deletarProduto(1L);

        //Verifica
        verify(produtoRepository).delete(produto);

    }









}
