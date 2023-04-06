package com.serverless.core.dto;

import com.serverless.core.entidade.CodigoPromocional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
@AllArgsConstructor
@NoArgsConstructor
public class CodigoPromocionalDto {
    private String id;
    private String codigoPromocional;
    private String valorTarifa;
    private String numeroMesesBonificacao;
    private String descricaoProduto;

    public String getCodigoPromocional() {
        return codigoPromocional;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCodigoPromocional(String codigoPromocional) {
        this.codigoPromocional = codigoPromocional;
    }

    public void setValorTarifa(String valorTarifa) {
        this.valorTarifa = valorTarifa;
    }

    public void setNumeroMesesBonificacao(String numeroMesesBonificacao) {
        this.numeroMesesBonificacao = numeroMesesBonificacao;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public List<CodigoPromocionalDto> toModel(List<CodigoPromocional> codigos){
        return codigos.stream().map(CodigoPromocionalDto::coditoPromocionalToDto).collect(Collectors.toList());
    }

    private static CodigoPromocionalDto coditoPromocionalToDto(CodigoPromocional codigoPromocional) {
        CodigoPromocionalDto codigoDto = new CodigoPromocionalDto();

        codigoDto.setId(String.valueOf(codigoPromocional.getId()));
        codigoDto.setCodigoPromocional(codigoPromocional.getCodigoPromocional());
        codigoDto.setDescricaoProduto(codigoPromocional.getDescricaoProduto());
        codigoDto.setValorTarifa(String.valueOf(codigoPromocional.getValorTarifa()));
        codigoDto.setNumeroMesesBonificacao(String.valueOf(codigoPromocional.getNumeroMesesBonificacao()));

        return codigoDto;
    }

    @Override
    public String toString() {
        return "CodigoPromocionalDto{" +
                "id='" + id + '\'' +
                ", codigoPromocional='" + codigoPromocional + '\'' +
                ", valorTarifa='" + valorTarifa + '\'' +
                ", numeroMesesBonificacao='" + numeroMesesBonificacao + '\'' +
                ", descricaoProduto='" + descricaoProduto + '\'' +
                '}';
    }
}
