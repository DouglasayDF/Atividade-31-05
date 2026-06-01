package com.curso.atividade_31_05.pedido;

import com.curso.atividade_31_05.frete.TipoEnvio;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String cliente;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal valorPedido;

    @NotNull
    private BigDecimal valorFrete;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoEnvio tipoEnvio;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @NotNull
    private LocalDateTime criadoEm;

    protected Pedido() {
    }

    public Pedido(String cliente, BigDecimal valorPedido, BigDecimal valorFrete, TipoEnvio tipoEnvio) {
        this.cliente = cliente;
        this.valorPedido = valorPedido;
        this.valorFrete = valorFrete;
        this.tipoEnvio = tipoEnvio;
        this.status = StatusPedido.AGUARDANDO_PAGAMENTO;
        this.criadoEm = LocalDateTime.now();
    }

    public void alterarStatus(StatusPedido status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public BigDecimal getValorPedido() {
        return valorPedido;
    }

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public TipoEnvio getTipoEnvio() {
        return tipoEnvio;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }
}
