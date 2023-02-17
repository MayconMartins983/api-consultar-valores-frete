package com.example.testewipro.demo.testesunitarios.helper;

import com.example.testewipro.demo.dto.DataFromBrasilCep;
import com.example.testewipro.demo.dto.DataFromViaCep;
import com.example.testewipro.demo.dto.Regiao;

public class Helper {

    public static DataFromViaCep oneMockDataFromViaCep() {
        return DataFromViaCep
                .builder()
                .cep("01001-000")
                .logradouro("Praça da Sé")
                .complemento("lado ímpar")
                .bairro("Sé")
                .localidade("São Paulo")
                .uf("SP")
                .ibge("3550308")
                .gia("1004")
                .ddd("11")
                .siafi("7107")
                .erro(null)
                .build();
    }

    public static DataFromBrasilCep oneMockDataFromBrasilCep() {
        return DataFromBrasilCep
                .builder()
                .id(1)
                .nome("São Paulo")
                .regiao(new Regiao(1, "Se", "Sudeste"))
                .sigla("Sp")
                .build();
    }

    public static DataFromBrasilCep oneMockDataFromBrasilCepWithRegionNull() {
        return DataFromBrasilCep
                .builder()
                .id(1)
                .nome("São Paulo")
                .sigla("Sp")
                .build();
    }
}
