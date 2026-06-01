package com.curso.atividade_31_05.frete;

import java.math.BigDecimal;

public interface CalculadoraFrete {
    TipoEnvio tipo();

    BigDecimal calcular(BigDecimal valorPedido);
}
