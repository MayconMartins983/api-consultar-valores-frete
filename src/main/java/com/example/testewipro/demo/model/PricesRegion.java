package com.example.testewipro.demo.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_PRICES_REGION")
public class PricesRegion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Column(name = "REGION")
    private String region;

    @NotNull
    @Column(name = "PRICE")
    private BigDecimal price;

    public PricesRegion(String region, BigDecimal price) {
        this.region = region;
        this.price = price;
    }
}
