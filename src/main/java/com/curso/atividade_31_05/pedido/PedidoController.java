package com.curso.atividade_31_05.pedido;

import com.curso.atividade_31_05.pedido.dto.CriarPedidoRequest;
import com.curso.atividade_31_05.pedido.dto.PedidoResponse;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> criar(@RequestBody @Valid CriarPedidoRequest request) {
        Pedido pedido = pedidoService.criar(request);
        return ResponseEntity.created(URI.create("/pedidos/" + pedido.getId()))
                .body(PedidoResponse.from(pedido));
    }

    @GetMapping
    public List<PedidoResponse> listar() {
        return pedidoService.listar().stream()
                .map(PedidoResponse::from)
                .toList();
    }

    @GetMapping("/{id}")
    public PedidoResponse buscar(@PathVariable Long id) {
        return PedidoResponse.from(pedidoService.buscar(id));
    }

    @PostMapping("/{id}/pagar")
    public PedidoResponse pagar(@PathVariable Long id) {
        return PedidoResponse.from(pedidoService.pagar(id));
    }

    @PostMapping("/{id}/cancelar")
    public PedidoResponse cancelar(@PathVariable Long id) {
        return PedidoResponse.from(pedidoService.cancelar(id));
    }

    @PostMapping("/{id}/enviar")
    public PedidoResponse enviar(@PathVariable Long id) {
        return PedidoResponse.from(pedidoService.enviar(id));
    }
}
