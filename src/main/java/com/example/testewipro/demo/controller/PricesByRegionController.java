package com.example.testewipro.demo.controller;

import com.example.testewipro.demo.controller.contractswagger.IPricesByRegionSwagger;
import com.example.testewipro.demo.dto.PricesRegionRequest;
import com.example.testewipro.demo.dto.PricesRegionResponse;
import com.example.testewipro.demo.service.PricesByRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/prices-region")
public class PricesByRegionController implements IPricesByRegionSwagger {

    @Autowired
    private PricesByRegionService service;

    @Override
    public List<PricesRegionResponse> getAllPricesByRegion() {
        return service.getAllPricesByRegion();
    }

    @Override
    public void updatePriceOfRegion(Long idRegion, PricesRegionRequest request) {
        service.updatePriceOfRegion(idRegion, request);
    }
}
