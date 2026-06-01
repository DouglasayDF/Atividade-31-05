package com.curso.atividade_31_05.pedido.dto;

import com.curso.atividade_31_05.frete.TipoEnvio;
import com.curso.atividade_31_05.pedido.Pedido;
import com.curso.atividade_31_05.pedido.StatusPedido;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PedidoResponse(
        Long id,
        String cliente,
        BigDecimal valorPedido,
        BigDecimal valorFrete,
        TipoEnvio tipoEnvio,
        StatusPedido status,
        LocalDateTime criadoEm
) {
    public static PedidoResponse from(Pedido pedido) {
        return new PedidoResponse(
                pedido.getId(),
                pedido.getCliente(),
                pedido.getValorPedido(),
                pedido.getValorFrete(),
                pedido.getTipoEnvio(),
                pedido.getStatus(),
                pedido.getCriadoEm()
        );
    }
}
