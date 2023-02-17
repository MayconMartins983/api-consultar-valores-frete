package com.example.testewipro.demo.controller.contractswagger;

import com.example.testewipro.demo.dto.PricesRegionRequest;
import com.example.testewipro.demo.dto.PricesRegionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Consult prices by region")
public interface IPricesByRegionSwagger {

    @GetMapping
    @Operation(summary = "End point to search all prices of regions")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Prices by region searched successfully")
    )
    List<PricesRegionResponse> getAllPricesByRegion();

    @PostMapping("id/{idRegion}")
    @Operation(summary = "End point to update price Freight of region")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Prices by region updated successfully")
    )
    void updatePriceOfRegion(@PathVariable Long idRegion, @RequestBody PricesRegionRequest request);
}
