package com.example.testewipro.demo.dto;

import com.example.testewipro.demo.model.PricesRegion;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PricesRegionResponse {

    private Long id;
    private String region;
    private BigDecimal price;

    public static PricesRegionResponse builderPricesRegionResponse(PricesRegion model) {
        return PricesRegionResponse
                .builder()
                .id(model.getId())
                .region(model.getRegion())
                .price(model.getPrice())
                .build();
    }
}
