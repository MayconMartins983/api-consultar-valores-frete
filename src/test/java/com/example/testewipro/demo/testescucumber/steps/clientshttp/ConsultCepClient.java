package com.example.testewipro.demo.testescucumber.steps.clientshttp;

import com.example.testewipro.demo.dto.ConsultCepRequest;
import com.example.testewipro.demo.dto.ConsultCepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "consultcep",
        url = "http://localhost:" + "${local.server.port}" + "/v1/consulta-endereco")
@Service
public interface ConsultCepClient {
    @PostMapping
    ConsultCepResponse consultFreightAndCep(@Valid @RequestBody ConsultCepRequest request);
}
