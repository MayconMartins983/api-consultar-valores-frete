package com.example.testewipro.demo.clientsCucumber.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PricesRegionRequest {

    private BigDecimal newPrice;
}
