package com.example.testewipro.demo.controller;

import com.example.testewipro.demo.controller.contractswagger.IConsultaCepSwagger;
import com.example.testewipro.demo.dto.ConsultCepRequest;
import com.example.testewipro.demo.dto.ConsultCepResponse;
import com.example.testewipro.demo.service.ConsultCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/consulta-endereco")
public class ConsultCepController implements IConsultaCepSwagger {

    @Autowired
    private ConsultCepService service;

    @Override
    public ConsultCepResponse consultFreightAndCep(ConsultCepRequest request) {
        return service.consultFreightAndCep(request.getCep());
    }
}
