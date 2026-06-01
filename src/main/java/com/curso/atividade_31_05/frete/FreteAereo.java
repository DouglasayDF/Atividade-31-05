package com.curso.atividade_31_05.frete;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Component;

@Component
public class FreteAereo implements CalculadoraFrete {
    private static final BigDecimal TAXA = new BigDecimal("0.10");

    @Override
    public TipoEnvio tipo() {
        return TipoEnvio.AEREO;
    }

    @Override
    public BigDecimal calcular(BigDecimal valorPedido) {
        return valorPedido.multiply(TAXA).setScale(2, RoundingMode.HALF_UP);
    }
}
