package com.estoque.estoque_api.service;

import com.estoque.estoque_api.dto.EntradaEstoqueDTO;
import com.estoque.estoque_api.exception.BusinessException;
import com.estoque.estoque_api.mapper.EntradaEstoqueMapper;
import com.estoque.estoque_api.model.EntradaEstoque;
import com.estoque.estoque_api.repository.EntradaEstoqueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EntradaEstoqueServiceTeste {

    @Mock
    EntradaEstoqueMapper entradaEstoqueMapper;

    @Mock
    EntradaEstoqueRepository entradaEstoqueRepository;

    @InjectMocks
    EntradaEstoqueService entradaEstoqueService;

    private EntradaEstoque entradaEstoque;
    private EntradaEstoqueDTO entradaEstoqueDTO;

    @BeforeEach
    void setup(){
        entradaEstoque = new EntradaEstoque();
        entradaEstoque.setId(1L);
        entradaEstoque.setQuantidade(1500);
        entradaEstoque.setEntradaEstoque(LocalDateTime.now());

        entradaEstoqueDTO = new EntradaEstoqueDTO();
        entradaEstoqueDTO.setId(1L);
        entradaEstoqueDTO.setQuantidade(1500);
        entradaEstoqueDTO.setEntradaEstoque(LocalDateTime.now());

    }

    @Test
    void buscarEntradaEstoqueComSucesso(){
        //Preparar
        when(entradaEstoqueRepository.findById(1L)).thenReturn(Optional.of(entradaEstoque));
        when(entradaEstoqueMapper.toDTO(entradaEstoque)).thenReturn(entradaEstoqueDTO);

        //Ação
        EntradaEstoqueDTO idEntradaEstoque = entradaEstoqueService.findById(1L);

        //Verificar
        assertNotNull(idEntradaEstoque);
        assertEquals(1L, idEntradaEstoque.getId());
        verify(entradaEstoqueRepository).findById(1L);
        verify(entradaEstoqueMapper).toDTO(entradaEstoque);

    }

    @Test
    void lancarBusinessExceptionQuandoIdNulo(){

        //Ação
        final BusinessException e = assertThrows(BusinessException.class, () ->{
            entradaEstoqueService.findById(null);
        });

        //Verificar
        assertThat(e, notNullValue());
        assertThat(e.getMessage(), containsString("Entrada de Estoque não encontrado"));
        assertThat(e.getCause(), nullValue());

    }

    @Test
    void criarRegistroEntradaEstoqueComSucesso(){
        //Preparar
        when(entradaEstoqueMapper.toEntity(entradaEstoqueDTO)).thenReturn(entradaEstoque);
        when(entradaEstoqueRepository.save(entradaEstoque)).thenReturn(entradaEstoque);
        when(entradaEstoqueMapper.toDTO(entradaEstoque)).thenReturn(entradaEstoqueDTO);

        //Ação
        EntradaEstoqueDTO entradaEstoqueCriado = entradaEstoqueService.registrarEntrada(entradaEstoqueDTO);

        //Verificar
        assertNotNull(entradaEstoqueCriado);
        assertEquals(1L, entradaEstoqueCriado.getId());
        verify(entradaEstoqueRepository).save(entradaEstoque);
    }

    @Test
    void listarEntradasComSucesso(){
        //Prepara
        when(entradaEstoqueRepository.findAll()).thenReturn(List.of(entradaEstoque));
        when(entradaEstoqueMapper.toDTO(entradaEstoque)).thenReturn(entradaEstoqueDTO);

        //Ação
        List<EntradaEstoqueDTO> entradaEstoqueDTOS = entradaEstoqueService.listarEntradas();

        //Verifica
        assertNotNull(entradaEstoqueDTOS);
        assertEquals(entradaEstoqueDTOS.size(), entradaEstoqueDTO.getId());
        assertEquals(entradaEstoqueDTOS.get(0).getId(), entradaEstoque.getId());
        verify(entradaEstoqueRepository).findAll();

    }

    @Test
    void lancarBusinessExceptionSeListaForVazia(){
        //Preparar
        when(entradaEstoqueRepository.findAll()).thenReturn(Collections.emptyList());

        //Ação
        BusinessException e = assertThrows(BusinessException.class, () ->{
            entradaEstoqueService.listarEntradas();
        });

        //Verificar
        assertThat(e.getMessage(), is("Nenhuma entrada encontrada"));
        verify(entradaEstoqueRepository).findAll();
        verifyNoMoreInteractions(entradaEstoqueRepository);

    }

    @Test
    void removerEntradaComSucesso(){
        //Prepara
        when(entradaEstoqueRepository.findById(1L)).thenReturn(Optional.of(entradaEstoque));

        //Ação
        entradaEstoqueService.deletarEntrada(1L);

        //Verifica
        verify(entradaEstoqueRepository).deleteById(entradaEstoque.getId());
    }

}
