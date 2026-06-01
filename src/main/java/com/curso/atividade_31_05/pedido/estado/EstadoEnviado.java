package com.curso.atividade_31_05.pedido.estado;

import com.curso.atividade_31_05.pedido.StatusPedido;
import org.springframework.stereotype.Component;

@Component
public class EstadoEnviado extends EstadoPedidoBase {
    @Override
    public StatusPedido status() {
        return StatusPedido.ENVIADO;
    }
}
