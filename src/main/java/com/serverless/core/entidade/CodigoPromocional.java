package com.serverless.core.entidade;

import java.math.BigDecimal;

public class CodigoPromocional {
    private Long id;
    private String codigoPromocional;
    private BigDecimal valorTarifa;
    private Double numeroMesesBonificacao;
    private String descricaoProduto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoPromocional() {
        return codigoPromocional;
    }

    public void setCodigoPromocional(String codigoPromocional) {
        this.codigoPromocional = codigoPromocional;
    }

    public BigDecimal getValorTarifa() {
        return valorTarifa;
    }

    public void setValorTarifa(BigDecimal valorTarifa) {
        this.valorTarifa = valorTarifa;
    }

    public Double getNumeroMesesBonificacao() {
        return numeroMesesBonificacao;
    }

    public void setNumeroMesesBonificacao(Double numeroMesesBonificacao) {
        this.numeroMesesBonificacao = numeroMesesBonificacao;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }
}
