package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.dto.CarDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntryServiceTest {

    EntryService entryService = EntryService.INSTANCE;

    @Test
    void processEntry() {
        CarDTO carDTO = CarDTO.builder()
                .carNumber("12가3457")
                .carTypeCode("disabled")
                .phone("010-1234-5678")
                .driverName("김철수")
                .build();
        entryService.processEntry(carDTO);
    }
}