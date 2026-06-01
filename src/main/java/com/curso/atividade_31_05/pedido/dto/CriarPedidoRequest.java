package com.curso.atividade_31_05.pedido.dto;

import com.curso.atividade_31_05.frete.TipoEnvio;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record CriarPedidoRequest(
        @NotBlank String cliente,
        @NotNull @DecimalMin(value = "0.01") BigDecimal valorPedido,
        @NotNull TipoEnvio tipoEnvio
) {
}
