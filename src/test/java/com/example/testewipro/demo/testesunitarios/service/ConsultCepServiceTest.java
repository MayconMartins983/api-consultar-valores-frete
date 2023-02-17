package com.example.testewipro.demo.testesunitarios.service;

import com.example.testewipro.demo.clients.ConsultCepClient;
import com.example.testewipro.demo.dto.ConsultCepResponse;
import com.example.testewipro.demo.exceptions.ValidationExceptionCustom;
import com.example.testewipro.demo.repository.PricesRegionRepository;
import com.example.testewipro.demo.service.ConsultCepService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static com.example.testewipro.demo.testesunitarios.helper.Helper.oneMockDataFromViaCep;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ConsultCepServiceTest {

    @InjectMocks
    private ConsultCepService service;

    @Mock
    private ConsultCepClient client;

    @Mock
    private PricesRegionRepository repository;

    @Test
    void consultFreightAndCep_shouldSeekAddressAndShipping_ifRequested() {
        when(client.findAdressesByCep("01001000"))
                .thenReturn(oneMockDataFromViaCep());
        when(client.findRegionByUf("SP"))
                .thenReturn("Sudeste");
        when(repository.findPriceOfFreightByRegion("Sudeste"))
                .thenReturn(new BigDecimal("7.85"));

        ConsultCepResponse response = service.consultFreightAndCep("01001000");

        assertThat(response)
                .extracting("cep", "rua", "complemento", "bairro", "cidade", "estado", "frete")
                .containsExactlyInAnyOrder("01001-000", "Praça da Sé", "lado ímpar", "Sé", "São Paulo",
                        "SP", new BigDecimal("7.85"));

        verify(client, times(1)).findAdressesByCep("01001000");
        verify(client, times(1)).findRegionByUf("SP");
        verify(repository, times(1)).findPriceOfFreightByRegion("Sudeste");
    }

    @Test
    void consultFreightAndCep_shouldSeekAddressAndShipping_ifCepWithHyphen() {
        when(client.findAdressesByCep("01001000"))
                .thenReturn(oneMockDataFromViaCep());
        when(client.findRegionByUf("SP"))
                .thenReturn("Sudeste");
        when(repository.findPriceOfFreightByRegion("Sudeste"))
                .thenReturn(new BigDecimal("7.85"));

        ConsultCepResponse response = service.consultFreightAndCep("01001-000");

        assertThat(response)
                .extracting("cep", "rua", "complemento", "bairro", "cidade", "estado", "frete")
                .containsExactlyInAnyOrder("01001-000", "Praça da Sé", "lado ímpar", "Sé", "São Paulo",
                        "SP", new BigDecimal("7.85"));

        verify(client, times(1)).findAdressesByCep("01001000");
        verify(client, times(1)).findRegionByUf("SP");
        verify(repository, times(1)).findPriceOfFreightByRegion("Sudeste");
    }

    @Test
    void consultFreightAndCep_shouldThrowException_ifCepLengthDifferentEight() {
        when(client.findAdressesByCep("010010002"))
                .thenReturn(oneMockDataFromViaCep());

        assertThatExceptionOfType(ValidationExceptionCustom.class)
                .isThrownBy(() -> service.consultFreightAndCep("01001-000234"))
                .withMessage("zip code with size invalid");

        verify(client, never()).findAdressesByCep(anyString());
        verify(client, never()).findRegionByUf(anyString());
        verify(repository, never()).findPriceOfFreightByRegion(anyString());
    }
}
