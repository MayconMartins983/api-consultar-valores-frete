package com.example.testewipro.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataFromBrasilCep {

    private Integer id;
    private String sigla;
    private String nome;
    private Regiao regiao;

}
