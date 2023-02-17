package com.example.testewipro.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultCepRequest {

    @NotBlank(message = "cep cannot be null")
    private String cep;
}
