package com.example.testewipro.demo.testesunitarios.repository;

import com.example.testewipro.demo.model.PricesRegion;
import com.example.testewipro.demo.repository.PricesRegionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class PricesRegionRepositoryTest {

    @Autowired
    private PricesRegionRepository repository;

    @BeforeEach
    public void before() {
        repository.deleteAll();
        repository.save(new PricesRegion("Test Region", new BigDecimal("100.00")));
    }

    @AfterEach
    public void after() {
        repository.deleteAll();
    }

    @Test
    void findPriceOfFreightByRegion_shouldReturnPrice_ifRequested() {
        BigDecimal priceTest = repository.findPriceOfFreightByRegion("test Region");

        assertThat(priceTest).isEqualTo(new BigDecimal("100.00"));
    }
}
