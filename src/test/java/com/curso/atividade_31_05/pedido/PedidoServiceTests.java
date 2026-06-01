package com.curso.atividade_31_05.pedido;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.curso.atividade_31_05.frete.TipoEnvio;
import com.curso.atividade_31_05.pedido.dto.CriarPedidoRequest;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class PedidoServiceTests {
    @Autowired
    private PedidoService pedidoService;

    @Test
    void deveCriarPedidoAguardandoPagamentoComFreteTerrestre() {
        Pedido pedido = pedidoService.criar(new CriarPedidoRequest(
                "Maria",
                new BigDecimal("200.00"),
                TipoEnvio.TERRESTRE
        ));

        assertThat(pedido.getStatus()).isEqualTo(StatusPedido.AGUARDANDO_PAGAMENTO);
        assertThat(pedido.getValorFrete()).isEqualByComparingTo("10.00");
    }

    @Test
    void deveCriarPedidoComFreteAereo() {
        Pedido pedido = pedidoService.criar(new CriarPedidoRequest(
                "Joao",
                new BigDecimal("200.00"),
                TipoEnvio.AEREO
        ));

        assertThat(pedido.getValorFrete()).isEqualByComparingTo("20.00");
    }

    @Test
    void devePermitirPagarEEnviarPedido() {
        Pedido pedido = pedidoService.criar(new CriarPedidoRequest(
                "Ana",
                new BigDecimal("150.00"),
                TipoEnvio.TERRESTRE
        ));

        pedidoService.pagar(pedido.getId());
        Pedido enviado = pedidoService.enviar(pedido.getId());

        assertThat(enviado.getStatus()).isEqualTo(StatusPedido.ENVIADO);
    }

    @Test
    void naoDevePermitirPagarPedidoDuasVezes() {
        Pedido pedido = pedidoService.criar(new CriarPedidoRequest(
                "Carlos",
                new BigDecimal("90.00"),
                TipoEnvio.AEREO
        ));

        pedidoService.pagar(pedido.getId());

        assertThatThrownBy(() -> pedidoService.pagar(pedido.getId()))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void naoDevePermitirAlterarPedidoCancelado() {
        Pedido pedido = pedidoService.criar(new CriarPedidoRequest(
                "Beatriz",
                new BigDecimal("50.00"),
                TipoEnvio.TERRESTRE
        ));

        pedidoService.cancelar(pedido.getId());

        assertThatThrownBy(() -> pedidoService.pagar(pedido.getId()))
                .isInstanceOf(IllegalStateException.class);
    }
}
