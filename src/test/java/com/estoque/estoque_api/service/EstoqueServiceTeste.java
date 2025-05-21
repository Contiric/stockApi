package com.estoque.estoque_api.service;

import com.estoque.estoque_api.dto.EstoqueDTO;
import com.estoque.estoque_api.exception.BusinessException;
import com.estoque.estoque_api.mapper.EstoqueMapper;
import com.estoque.estoque_api.model.Estoque;
import com.estoque.estoque_api.repository.EstoqueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EstoqueServiceTeste {

    @Mock
    EstoqueMapper estoqueMapper;

    @Mock
    EstoqueRepository estoqueRepository;

    @InjectMocks
    EstoqueService estoqueService;

    private Estoque estoque;
    private EstoqueDTO estoqueDTO;

    @BeforeEach
    void setUp() {
        estoque = new Estoque();
        estoque.setId(1L);
        estoque.setQuantidade(100);

        estoqueDTO = new EstoqueDTO();
        estoqueDTO.setId(1L);
        estoqueDTO.setQuantidade(100);
        estoqueDTO.setProdutoIds(List.of(10L, 20L));
    }

    @Test
    void criarEstoque() {
        // Arrange
        Estoque estoqueEntity = new Estoque();
        estoqueEntity.setQuantidade(100);

        Estoque estoqueSalvo = new Estoque();
        estoqueSalvo.setId(1L);
        estoqueSalvo.setQuantidade(100);

        EstoqueDTO dto = new EstoqueDTO();
        dto.setQuantidade(100);
        dto.setProdutoIds(List.of(10L));

        EstoqueDTO dtoSalvo = new EstoqueDTO();
        dtoSalvo.setId(1L);
        dtoSalvo.setQuantidade(100);
        dtoSalvo.setProdutoIds(List.of(10L));

        when(estoqueMapper.toEntity(dto)).thenReturn(estoqueEntity);
        when(estoqueRepository.save(any(Estoque.class))).thenReturn(estoqueSalvo); // <- aqui!
        when(estoqueMapper.toDTO(estoqueSalvo)).thenReturn(dtoSalvo);

        // Act
        EstoqueDTO resultado = estoqueService.criarEstoque(dto);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(estoqueRepository).save(any(Estoque.class)); // ou verify com argumento capturado, se quiser validar conteúdo
    }


    @Test
    void buscarId(){

        //Arranjar
        when(estoqueRepository.findById(1L)).thenReturn(Optional.of(estoque));
        when(estoqueMapper.toDTO(estoque)).thenReturn(estoqueDTO);

        //Ação
        EstoqueDTO idEncontrado = estoqueService.findById(1L);

        //Verfica
        assertNotNull(idEncontrado);
        assertEquals(1L, idEncontrado.getId());
        verify(estoqueRepository).findById(1L);
        verify(estoqueMapper).toDTO(estoque);

    }

    @Test
    void listarEstoque(){

        //Preparar
        when(estoqueRepository.findAll()).thenReturn(List.of(estoque));
        when(estoqueMapper.toDTO(estoque)).thenReturn(estoqueDTO);

        //Ação
        List<EstoqueDTO> estoqueDTOS = estoqueService.listarEstoque();

        //Verifica
        assertEquals(1L, estoqueDTOS.get(0).getId());
        assertEquals(1, estoqueDTOS.size());
        verify(estoqueMapper).toDTO(estoque);
        verify(estoqueRepository).findAll();
    }

    @Test
    void atualizarEstoque() {
        Estoque existente = new Estoque();
        existente.setId(1L);
        existente.setQuantidade(50);

        Estoque atualizado = new Estoque();
        atualizado.setId(1L);
        atualizado.setQuantidade(100);

        EstoqueDTO dtoEntrada = new EstoqueDTO();
        dtoEntrada.setQuantidade(100);
        dtoEntrada.setProdutoIds(List.of(10L));

        EstoqueDTO dtoEsperado = new EstoqueDTO();
        dtoEsperado.setId(1L);
        dtoEsperado.setQuantidade(100);
        dtoEsperado.setProdutoIds(List.of(10L));

        when(estoqueRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(estoqueRepository.save(any(Estoque.class))).thenReturn(atualizado); // aqui!
        when(estoqueMapper.toDTO(atualizado)).thenReturn(dtoEsperado);

        EstoqueDTO resultado = estoqueService.atualizar(1L, dtoEntrada);

        assertEquals(100, resultado.getQuantidade());
        assertEquals(1L, resultado.getId());
        verify(estoqueRepository).save(any(Estoque.class));
    }

    @Test
    void deletarEstoqueComSucesso(){
        Estoque estoque = new Estoque();
        estoque.setId(1L);

        //Prepara
        when(estoqueRepository.findById(1L)).thenReturn(Optional.of(estoque));

        //Ação
        estoqueService.removerEstoque(1L);

        //Verifica
        verify(estoqueRepository).delete(estoque);

    }

    @Test
    void deletarEstoqueNotFoundThrowsBusinnesException(){
        Long idNaoExistente = 2L;

        //Preparar
        when(estoqueRepository.findById(idNaoExistente)).thenReturn(Optional.empty());

        //Ação
        BusinessException ex = assertThrows(BusinessException.class, () -> {
            estoqueService.removerEstoque(idNaoExistente);
        });

        //Verifica
        assertEquals("Estoque não encontrado com ID: 2", ex.getMessage());
        verify(estoqueRepository, never()).deleteById(any());

    }

}
