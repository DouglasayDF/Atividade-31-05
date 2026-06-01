package com.curso.atividade_31_05.pedido.estado;

import com.curso.atividade_31_05.pedido.Pedido;
import com.curso.atividade_31_05.pedido.StatusPedido;
import org.springframework.stereotype.Component;

@Component
public class EstadoPago extends EstadoPedidoBase {
    @Override
    public StatusPedido status() {
        return StatusPedido.PAGO;
    }

    @Override
    public void cancelar(Pedido pedido) {
        pedido.alterarStatus(StatusPedido.CANCELADO);
    }

    @Override
    public void enviar(Pedido pedido) {
        pedido.alterarStatus(StatusPedido.ENVIADO);
    }
}
