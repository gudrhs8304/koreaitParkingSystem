package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.dto.CarDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExitServiceTest {
    ExitService exitService = ExitService.INSTANCE;
    CarselectService carselectService = CarselectService.INSTANCE;
    DiscountPolicyService discountPolicyService = DiscountPolicyService.INSTANCE;

    @Test
    void printCarType() {
        CarDTO carDTO = CarDTO.builder()
                .carNumber("12가3456").build();
        carselectService.printCarType(carDTO);
    }








//    @Test
//    void searchCar() {
//        CarDTO carDTO = CarDTO.builder()
//                .carNumber("12가3456").build();
//        exitService.searchCar(carDTO);
//    }
}