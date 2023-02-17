package com.example.testewipro.demo.controller.contractswagger;

import com.example.testewipro.demo.dto.ConsultCepRequest;
import com.example.testewipro.demo.dto.ConsultCepResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Tag(name = "Consult zip code and address")
public interface IConsultaCepSwagger {

    @PostMapping
    @Operation(summary = "End point to search for address and shipping price by region")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Shipping and address searched successfully")
    )
    ConsultCepResponse consultFreightAndCep(@Valid @RequestBody ConsultCepRequest request);
}
