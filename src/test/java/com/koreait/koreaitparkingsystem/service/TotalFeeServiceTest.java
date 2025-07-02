package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.dto.CarDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TotalFeeServiceTest {
    TotalFeeService totalFeeService = TotalFeeService.INSTANCE;

    @Test
    void printTotalFee() {
        CarDTO carDTO = CarDTO.builder()
                .carNumber("90마1234").build();
        totalFeeService.printTotalFee(carDTO);
    }

}