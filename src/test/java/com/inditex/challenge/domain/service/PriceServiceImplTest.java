package com.inditex.challenge.domain.service;

import com.inditex.challenge.application.service.PriceServiceImpl;
import com.inditex.challenge.domain.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

class PriceServiceImplTest {
    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceServiceImpl priceServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldInitializeCorrectly() {
        assertNotNull(priceServiceImpl);
        assertNotNull(priceRepository);
    }

    @Test
    void shouldImplementPriceServiceInterface() {
        assertInstanceOf(PriceService.class, priceServiceImpl);
    }

}
