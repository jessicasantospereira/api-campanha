package com.serverless.core.dto;

import com.serverless.core.entidade.Bin;

import java.util.List;
import java.util.stream.Collectors;

public class BinDto {
    private Long idBin;
    private String valorBin;

    public void setIdBin(Long idBin) {
        this.idBin = idBin;
    }

    public void setValorBin(String valorBin) {
        this.valorBin = valorBin;
    }

    public List<BinDto> toEntity(List<Bin> bin){
        return bin.stream().map(BinDto::binToDto).collect(Collectors.toList());
    }

    public static BinDto binToDto(Bin bin){
        BinDto binDto = new BinDto();
        binDto.setIdBin(bin.getIdBin());
        binDto.setValorBin(bin.getValorBin());

        return binDto;
    }
}
