package com.curso.atividade_31_05.pedido.estado;

import com.curso.atividade_31_05.pedido.Pedido;
import com.curso.atividade_31_05.pedido.StatusPedido;

public interface EstadoPedido {
    StatusPedido status();

    void pagar(Pedido pedido);

    void cancelar(Pedido pedido);

    void enviar(Pedido pedido);
}
