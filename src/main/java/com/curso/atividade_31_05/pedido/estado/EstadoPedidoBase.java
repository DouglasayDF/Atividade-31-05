package com.curso.atividade_31_05.pedido.estado;

import com.curso.atividade_31_05.pedido.Pedido;

public abstract class EstadoPedidoBase implements EstadoPedido {
    @Override
    public void pagar(Pedido pedido) {
        throw new IllegalStateException("Pedido nao pode ser pago no status atual.");
    }

    @Override
    public void cancelar(Pedido pedido) {
        throw new IllegalStateException("Pedido nao pode ser cancelado no status atual.");
    }

    @Override
    public void enviar(Pedido pedido) {
        throw new IllegalStateException("Pedido nao pode ser enviado no status atual.");
    }
}
