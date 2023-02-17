package com.example.testewipro.demo.testesunitarios.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith({SpringExtension.class})
@ActiveProfiles("test")
@SpringBootTest
class ConsultCepControllerTest {

    private static final String URL = "/v1/consulta-endereco";

    @Autowired
    private MockMvc mvc;

    @Test
    @SneakyThrows
    void consultFreightAndCep_shouldReturnStatus200_ifRequested() {
        mvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cep\": \"01001000\"}"))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void consultFreightAndCep_shouldReturnStatus400_ifCepNull() {
        mvc.perform(post(URL))
                .andExpect(status().isBadRequest());
    }
}
