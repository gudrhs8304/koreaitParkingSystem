package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.dto.CarDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscountPolicyServiceTest {

    @Test
    void printDiscountPolicy() {
        CarDTO carDTO = CarDTO.builder()
                .carNumber("12가3456").build();
        DiscountPolicyService.printDiscountPolicy(carDTO);
    }

}