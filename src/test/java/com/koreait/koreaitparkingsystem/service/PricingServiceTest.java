package com.koreait.koreaitparkingsystem.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PricingServiceTest {

    @Test
    void updateFees() {
        PricingService.INSTANCE.updateFees(1,5000);

    }
}