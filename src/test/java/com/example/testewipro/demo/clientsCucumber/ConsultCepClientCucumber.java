package com.example.testewipro.demo.clientsCucumber;

import com.example.testewipro.demo.dto.PricesRegionRequest;
import com.example.testewipro.demo.dto.PricesRegionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "consult",
    url = "http://localhost:" + "${local.server.port}" + "/v1/prices-region")
@Service
public interface ConsultCepClientCucumber {

    @GetMapping
    List<PricesRegionResponse> consultFreightAndCep();

    @PutMapping("id/{idRegion}")
    void updatePriceOfRegion(@PathVariable Long idRegion, @RequestBody PricesRegionRequest request);
}
