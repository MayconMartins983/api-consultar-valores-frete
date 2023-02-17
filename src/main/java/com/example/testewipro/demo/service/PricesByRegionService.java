package com.example.testewipro.demo.service;

import com.example.testewipro.demo.dto.PricesRegionRequest;
import com.example.testewipro.demo.dto.PricesRegionResponse;
import com.example.testewipro.demo.exceptions.ResourceNotFoundException;
import com.example.testewipro.demo.model.PricesRegion;
import com.example.testewipro.demo.repository.PricesRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PricesByRegionService {

    @Autowired
    private PricesRegionRepository repository;

    public List<PricesRegionResponse> getAllPricesByRegion() {
        return repository.findAll()
                .stream()
                .map(PricesRegionResponse::builderPricesRegionResponse)
                .collect(Collectors.toList());
    }

    public void updatePriceOfRegion(Long regionId, PricesRegionRequest request) {
        PricesRegion pricesRegion = findByIdOrThrowException(regionId);
        pricesRegion.setPrice(request.getNewPrice());

        repository.save(pricesRegion);
    }

    private PricesRegion findByIdOrThrowException(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Region with id: " + id + " not found."));
    }
}
