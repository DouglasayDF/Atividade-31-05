package com.curso.atividade_31_05.pedido;

import com.curso.atividade_31_05.frete.FreteService;
import com.curso.atividade_31_05.pedido.dto.CriarPedidoRequest;
import com.curso.atividade_31_05.pedido.estado.EstadoPedidoFactory;
import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final FreteService freteService;
    private final EstadoPedidoFactory estadoPedidoFactory;

    public PedidoService(PedidoRepository pedidoRepository, FreteService freteService,
            EstadoPedidoFactory estadoPedidoFactory) {
        this.pedidoRepository = pedidoRepository;
        this.freteService = freteService;
        this.estadoPedidoFactory = estadoPedidoFactory;
    }

    @Transactional
    public Pedido criar(CriarPedidoRequest request) {
        BigDecimal valorFrete = freteService.calcular(request.tipoEnvio(), request.valorPedido());
        Pedido pedido = new Pedido(request.cliente(), request.valorPedido(), valorFrete, request.tipoEnvio());
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Pedido buscar(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido nao encontrado: " + id));
    }

    @Transactional
    public Pedido pagar(Long id) {
        Pedido pedido = buscar(id);
        estadoPedidoFactory.obter(pedido.getStatus()).pagar(pedido);
        return pedido;
    }

    @Transactional
    public Pedido cancelar(Long id) {
        Pedido pedido = buscar(id);
        estadoPedidoFactory.obter(pedido.getStatus()).cancelar(pedido);
        return pedido;
    }

    @Transactional
    public Pedido enviar(Long id) {
        Pedido pedido = buscar(id);
        estadoPedidoFactory.obter(pedido.getStatus()).enviar(pedido);
        return pedido;
    }
}
