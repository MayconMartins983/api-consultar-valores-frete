package com.example.testewipro.demo.controller;

import com.example.testewipro.demo.model.PricesRegion;
import com.example.testewipro.demo.repository.PricesRegionRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith({SpringExtension.class})
@ActiveProfiles("test")
@SpringBootTest
class PricesByRegionControllerTest {

    private static final String URL = "/v1/prices-region";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PricesRegionRepository repository;

    @Test
    @SneakyThrows
    void getAllPricesByRegion_shouldReturnStatusOk_ifRequested() {
        mvc.perform(get(URL))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void updatePriceOfRegion_shouldReturnStatusOk_ifRequested() {
        when(repository.findById(100L))
                        .thenReturn(Optional.of(new PricesRegion(100L, "Sudeste", new BigDecimal("7.75"))));

        mvc.perform(put(URL + "/id/100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"newPrice\":\"22.0\"}"))
                .andExpect(status().isOk());
    }
}
