package com.example.testewipro.demo.repository;

import com.example.testewipro.demo.model.PricesRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface PricesRegionRepository extends JpaRepository<PricesRegion, Long> {

    @Query(value = "select p.price from PricesRegion p where upper(p.region) = upper(:region)")
    BigDecimal findPriceOfFreightByRegion(String region);
}
