package com.example.testewipro.demo.service;

import com.example.testewipro.demo.clients.ConsultCepClient;
import com.example.testewipro.demo.dto.ConsultCepResponse;
import com.example.testewipro.demo.exceptions.ResourceNotFoundException;
import com.example.testewipro.demo.exceptions.ValidationExceptionCustom;
import com.example.testewipro.demo.repository.PricesRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultCepService {

    @Autowired
    private ConsultCepClient client;

    @Autowired
    private PricesRegionRepository repository;

    public ConsultCepResponse consultFreightAndCep(String cep) {
        var cepFormatted = processCep(cep);
        var address = client.findAdressesByCep(cepFormatted);

        if (address.getErro() != null) {
            throw new ResourceNotFoundException("zip code not found in the viaCep database.");
        } else {
            var uf = address.getUf();
            var region = client.findRegionByUf(uf);
            var priceFreight = repository.findPriceOfFreightByRegion(region);

            return ConsultCepResponse.builderConsultResponse(address, priceFreight);
        }
    }

    private String processCep(String cep) {
        String cepNumbers = getOnlyNumbers(cep);

        if (cepNumbers.length() == 8) {
            return cepNumbers;
        } else {
            throw new ValidationExceptionCustom("zip code with size invalid");
        }
    }

    private static String getOnlyNumbers(String value) {
        return value.replaceAll("[^0-9]", "");
    }
}
