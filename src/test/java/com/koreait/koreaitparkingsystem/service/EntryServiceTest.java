package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.dto.CarDTO;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntryServiceTest {

    EntryService entryService = EntryService.INSTANCE;

    @Test
    void processEntry() {
        CarDTO carDTO = CarDTO.builder()
                .carNumber("123460")
                .carTypeCode("electric")
                .phone("010-1234-5678")
                .driverName("김갑환")
                .build();
        entryService.processEntry(carDTO);
    }
}