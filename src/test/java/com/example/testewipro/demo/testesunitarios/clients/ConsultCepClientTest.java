package com.example.testewipro.demo.testesunitarios.clients;


import com.example.testewipro.demo.clients.ConsultCepClient;
import com.example.testewipro.demo.dto.DataFromBrasilCep;
import com.example.testewipro.demo.dto.DataFromViaCep;
import com.example.testewipro.demo.exceptions.ResourceNotFoundException;
import com.example.testewipro.demo.testesunitarios.helper.Helper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ConsultCepClientTest {

    public static final String URL_BRASIL_API = "https://brasilapi.com.br/api/ibge/uf/v1/SP";
    public static final String URL_VIA_CEP = "http://viacep.com.br/ws/01022365/json/";
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ConsultCepClient client;

    @BeforeEach
    public void before() {
        ReflectionTestUtils.setField(client, "urlApiCep", "http://viacep.com.br/ws/01022365/json/");
        ReflectionTestUtils.setField(client, "urlBrasilApi", "https://brasilapi.com.br/api/ibge/uf/v1/SP");
    }

    @Test
    void findAdressesByCep_shouldReturnAdress_ifRequested() {
        HashMap<String, String> parameterUrl = new HashMap<>();
        parameterUrl.put("cep", "01022365");
        when(restTemplate.getForObject(URL_VIA_CEP,
                DataFromViaCep.class, parameterUrl)).thenReturn(Helper.oneMockDataFromViaCep());

        var response = client.findAdressesByCep("01022365");

        assertThat(response).isEqualTo(Helper.oneMockDataFromViaCep());
    }

    @Test
    void findAdressesByCep_shouldThrowException_ifRequestFailed() {
        HashMap<String, String> parameterUrl = new HashMap<>();
        parameterUrl.put("cep", "01022365");
        doThrow(RestClientException.class).when(restTemplate).getForObject(URL_VIA_CEP,
                DataFromViaCep.class, parameterUrl);

        assertThrows(RestClientException.class, () -> restTemplate.getForObject(URL_VIA_CEP,
                DataFromViaCep.class, parameterUrl));
    }

    @Test
    void findRegionByUf_shouldReturnRegion_ifRequested() {
        HashMap<String, String> parameterUrl = new HashMap<>();
        parameterUrl.put("uf", "Sp");
        when(restTemplate.getForObject(URL_BRASIL_API,
                DataFromBrasilCep.class, parameterUrl)).thenReturn(Helper.oneMockDataFromBrasilCep());

        var response = client.findRegionByUf("Sp");

        assertThat(response).isEqualTo("Sudeste");
    }

    @Test
    void findRegionByUf_shouldThrowException_ifRequestFailed() {
        HashMap<String, String> parameterUrl = new HashMap<>();
        parameterUrl.put("cep", "01022365");
        doThrow(RestClientException.class).when(restTemplate).getForObject(URL_BRASIL_API,
                DataFromViaCep.class, parameterUrl);

        assertThrows(RestClientException.class, () -> restTemplate.getForObject(URL_BRASIL_API,
                DataFromViaCep.class, parameterUrl));
    }

    @Test
    void findRegionByUf_shouldThrowException_ifDataNull() {
        HashMap<String, String> parameterUrl = new HashMap<>();
        parameterUrl.put("uf", "Sp");
        when(restTemplate.getForObject(URL_BRASIL_API,
                DataFromBrasilCep.class, parameterUrl)).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> client.findRegionByUf("Sp"));
    }

    @Test
    void findRegionByUf_shouldThrowException_ifRegionNull() {
        HashMap<String, String> parameterUrl = new HashMap<>();
        parameterUrl.put("uf", "Sp");
        when(restTemplate.getForObject(URL_BRASIL_API,
                DataFromBrasilCep.class, parameterUrl)).thenReturn(Helper.oneMockDataFromBrasilCepWithRegionNull());

        assertThrows(ResourceNotFoundException.class, () -> client.findRegionByUf("Sp"));
    }
}
