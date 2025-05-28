package com.estoque.estoque_api.service;

import com.estoque.estoque_api.dto.SaidaEstoqueDTO;
import com.estoque.estoque_api.exception.BusinessException;
import com.estoque.estoque_api.mapper.SaidaEstoqueMapper;
import com.estoque.estoque_api.model.SaidaEstoque;
import com.estoque.estoque_api.repository.SaidaEstoqueRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SaidaEstoqueServiceTeste {

    @Mock
    private SaidaEstoqueRepository saidaEstoqueRepository;

    @Mock
    private SaidaEstoqueMapper saidaEstoqueMapper;

    @InjectMocks
    private SaidaEstoqueService saidaEstoqueService;

    private SaidaEstoque saidaEstoque;
    private SaidaEstoqueDTO saidaEstoqueDTO;

    @BeforeEach
    void setup(){
        saidaEstoque = new SaidaEstoque();
        saidaEstoque.setId(1L);
        saidaEstoque.setDataSaida(LocalDateTime.now());
        saidaEstoque.setQuantidade(50);

        saidaEstoqueDTO = new SaidaEstoqueDTO();
        saidaEstoqueDTO.setId(1L);
        saidaEstoqueDTO.setDataSaida(LocalDateTime.now());
        saidaEstoqueDTO.setQuantidade(50);
    }

    @Test
    void buscarPorId(){
        //Prepara
        when(saidaEstoqueRepository.findById(1L)).thenReturn(Optional.of(saidaEstoque));
        when(saidaEstoqueMapper.toDTO(saidaEstoque)).thenReturn(saidaEstoqueDTO);

        //Ação
        SaidaEstoqueDTO saidaById = saidaEstoqueService.findById(saidaEstoque.getId());

        //Verifica
        Assertions.assertNotNull(saidaById);
        Assertions.assertEquals(1L, saidaById.getId());
        verify(saidaEstoqueRepository).findById(1L);
        verifyNoMoreInteractions(saidaEstoqueRepository);

    }

    @Test
    void deveLancarBusinessExceptionSeIdForNull(){
        //Preparar
        when(saidaEstoqueRepository.findById(null)).thenReturn(Optional.empty());

        //Ação
        BusinessException e = assertThrows(BusinessException.class, () ->{
            saidaEstoqueService.findById(null);
        });

        //Verifica
        assertThat(e.getMessage(), CoreMatchers.containsString("Saída não encontrado com ID"));
        assertThat(e.getCause(), nullValue());
    }

    @Test
    void listarSaidasEstoqueComSucesso(){
        //Preparar
        when(saidaEstoqueRepository.findAll()).thenReturn(List.of(saidaEstoque));
        when(saidaEstoqueMapper.toDTO(saidaEstoque)).thenReturn(saidaEstoqueDTO);

        //Ação
        List<SaidaEstoqueDTO> listSaidas = saidaEstoqueService.findAll();

        //Verificar
        assertNotNull(listSaidas);
        assertEquals(listSaidas.size(), saidaEstoqueDTO.getId());
        assertEquals(listSaidas.get(0).getId(), saidaEstoqueDTO.getId());
        verify(saidaEstoqueRepository).findAll();
        verify(saidaEstoqueRepository).findAll();

    }

    @Test
    void registrarSaidaComSucesso(){
        //Preparar
        when(saidaEstoqueMapper.toEntity(saidaEstoqueDTO)).thenReturn(saidaEstoque);
        when(saidaEstoqueRepository.save(saidaEstoque)).thenReturn(saidaEstoque);
        when(saidaEstoqueMapper.toDTO(saidaEstoque)).thenReturn(saidaEstoqueDTO);

        //Ação
        SaidaEstoqueDTO registroSaida = saidaEstoqueService.registrarSaida(saidaEstoqueDTO);

        //Verifica
        assertNotNull(registroSaida);
        assertEquals(1L, registroSaida.getId());
        verify(saidaEstoqueRepository).save(saidaEstoque);
    }

    @Test
    void deletarSaidaComSucesso(){
        //Prepara
        when(saidaEstoqueRepository.findById(1L)).thenReturn(Optional.of(saidaEstoque));

        //Ação
        saidaEstoqueService.deletarSaida(1L);

        //Verifica
        verify(saidaEstoqueRepository).delete(saidaEstoque);

    }

}
