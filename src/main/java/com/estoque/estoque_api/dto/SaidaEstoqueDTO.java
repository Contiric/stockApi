package com.estoque.estoque_api.dto;

import com.estoque.estoque_api.model.Produto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
@Getter
@Setter
public class SaidaEstoqueDTO {

    public Long id;

    @NotNull(message = "O id do produto é obrigatória")
    public Long produtoId;

    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 1, message = "A quantidade mínima deve ser 1")
    public int quantidade;

    @NotNull(message = "A data de saída é obrigatória")
    public LocalDateTime dataSaida;

}
