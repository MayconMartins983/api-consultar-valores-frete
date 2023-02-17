package com.example.testewipro.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultCepResponse {

    private String cep;
    private String rua;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private BigDecimal frete;

    public static ConsultCepResponse builderConsultResponse(DataFromViaCep dataFromViaCep, BigDecimal price) {
        return ConsultCepResponse
                .builder()
                .cep(dataFromViaCep.getCep())
                .rua(dataFromViaCep.getLogradouro())
                .complemento(dataFromViaCep.getComplemento())
                .bairro(dataFromViaCep.getBairro())
                .cidade(dataFromViaCep.getLocalidade())
                .estado(dataFromViaCep.getUf())
                .frete(price)
                .build();
    }
}
