package com.curso.atividade_31_05.frete;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class FreteService {
    private final Map<TipoEnvio, CalculadoraFrete> calculadoras = new EnumMap<>(TipoEnvio.class);

    public FreteService(List<CalculadoraFrete> calculadoras) {
        calculadoras.forEach(calculadora -> this.calculadoras.put(calculadora.tipo(), calculadora));
    }

    public BigDecimal calcular(TipoEnvio tipoEnvio, BigDecimal valorPedido) {
        CalculadoraFrete calculadora = calculadoras.get(tipoEnvio);
        if (calculadora == null) {
            throw new IllegalArgumentException("Tipo de envio nao suportado: " + tipoEnvio);
        }
        return calculadora.calcular(valorPedido);
    }
}
