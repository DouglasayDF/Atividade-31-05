package com.curso.atividade_31_05.pedido.estado;

import com.curso.atividade_31_05.pedido.StatusPedido;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class EstadoPedidoFactory {
    private final Map<StatusPedido, EstadoPedido> estados = new EnumMap<>(StatusPedido.class);

    public EstadoPedidoFactory(List<EstadoPedido> estados) {
        estados.forEach(estado -> this.estados.put(estado.status(), estado));
    }

    public EstadoPedido obter(StatusPedido status) {
        EstadoPedido estado = estados.get(status);
        if (estado == null) {
            throw new IllegalArgumentException("Status nao suportado: " + status);
        }
        return estado;
    }
}
