package com.estoque.estoque_api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EntradaEstoqueDTO {

    public Long id;

    @NotNull(message = "O ID do produto é obrigatório")
    public Long produtoId;

    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 1, message = "A quantidade mínima deve ser 1")
    public Integer quantidade;

    @NotNull(message = "A data de entrada é obrigatória")
    public LocalDateTime entradaEstoque;
}
