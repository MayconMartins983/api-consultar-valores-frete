package com.example.testewipro.demo.service;

import com.example.testewipro.demo.dto.PricesRegionRequest;
import com.example.testewipro.demo.dto.PricesRegionResponse;
import com.example.testewipro.demo.exceptions.ResourceNotFoundException;
import com.example.testewipro.demo.model.PricesRegion;
import com.example.testewipro.demo.repository.PricesRegionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PricesByRegionServiceTest {

    @Mock
    private PricesRegionRepository repository;

    @InjectMocks
    private PricesByRegionService service;

    @Captor
    private ArgumentCaptor<PricesRegion> captor;

    @Test
    void updatePriceOfRegion_shouldUpdatedPriceRegion_ifRequested() {
        when(repository.findById(1L))
                .thenReturn(Optional.of(new PricesRegion(1L, "Sudeste", new BigDecimal("7.75"))));

        service.updatePriceOfRegion(1L, new PricesRegionRequest(new BigDecimal("2.36")));

        verify(repository, times(1)).save(captor.capture());

        var valueCaptured = captor.getValue();

        assertThat(valueCaptured).isEqualTo(new PricesRegion(1L, "Sudeste", new BigDecimal("2.36")));
    }

    @Test
    void getAllPricesByRegion_shouldReturnListPricesRegionResponse_IfRequested() {
        when(repository.findAll())
                .thenReturn(List.of(new PricesRegion(1L, "Sudeste", new BigDecimal("7.75")),
                        new PricesRegion(2L, "Sul", new BigDecimal("72.75"))));

        var response = service.getAllPricesByRegion();

        response.forEach(object -> assertThat(object).isExactlyInstanceOf(PricesRegionResponse.class));
        assertThat(response)
                .hasSize(2)
                .extracting("id", "region", "price")
                .containsExactlyInAnyOrder(tuple(1L, "Sudeste", new BigDecimal("7.75")),
                        tuple(2L, "Sul", new BigDecimal("72.75")));
    }

    @Test
    void updatePriceOfRegion_shouldThrowException_ifPriceRegionNotFound() {
        doThrow(ResourceNotFoundException.class)
                .when(repository).findById(1L);

        assertThrows(ResourceNotFoundException.class, () -> service
                        .updatePriceOfRegion(1L, new PricesRegionRequest(new BigDecimal("2.36"))));

    }
}
