package com.example.testewipro.demo.clients;

import com.example.testewipro.demo.dto.DataFromBrasilCep;
import com.example.testewipro.demo.dto.DataFromViaCep;
import com.example.testewipro.demo.exceptions.ResourceNotFoundException;
import com.example.testewipro.demo.exceptions.ValidationExceptionCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class ConsultCepClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value(value = "${app-data.clients-url.via-cep}")
    private String urlApiCep;

    @Value(value = "${app-data.clients-url.brasil-api}")
    private String urlBrasilApi;

    public DataFromViaCep findAdressesByCep(String cep) {
        HashMap<String, String> parameterUrl = new HashMap<>();
        parameterUrl.put("cep", cep);

        try {
            return restTemplate.getForObject(urlApiCep, DataFromViaCep.class, parameterUrl);
        } catch (RestClientException ex) {
            throw new ValidationExceptionCustom("Error when trying to get address in viaCep.");
        }

    }

    public String findRegionByUf(String uf) {
        HashMap<String, String> parameterUrl = new HashMap<>();
        parameterUrl.put("uf", uf);

        try {
            var data = restTemplate.getForObject(urlBrasilApi, DataFromBrasilCep.class, parameterUrl);

            if (data != null && data.getRegiao() != null) {
                return data.getRegiao().getNome();
            } else {
                throw new ResourceNotFoundException("it was not possible to search for region by uf");
            }
        } catch (RestClientException ex) {
            throw new ValidationExceptionCustom("Error when trying to get region in brasilApi.");
        }
    }
}
