package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.dto.CarDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExitServiceTest {
    ExitService exitService = new ExitService();

    @Test
    void printCarType() {
        CarDTO carDTO = CarDTO.builder()
                .carNumber("12가3456").build();
        exitService.printCarType(carDTO);
    }

    @Test
    void printDiscountPolicy() {
        CarDTO carDTO = CarDTO.builder()
                .carNumber("12가3456").build();
        exitService.printDiscountPolicy(carDTO);
    }

    @Test
    void printMonthlyMember() {
        CarDTO carDTO = CarDTO.builder()
                .carNumber("12가3456").build();
        exitService.printMonthlyMember(carDTO);
    }

    @Test
    void updateParkingLog() {
        CarDTO carDTO = CarDTO.builder()
                .carNumber("56다1234").build();
        exitService.updateParkingLog(carDTO);
    }

    @Test
    void printTotalFee() {
        CarDTO carDTO = CarDTO.builder()
                .carNumber("90마1234").build();
        exitService.printTotalFee(carDTO);
    }

    @Test
    void isParkingSpot() {
        CarDTO carDTO = CarDTO.builder()
                .carNumber("88바8888").build();
        exitService.isParkingSpot(carDTO);
    }

    @Test
    void searchCar() {
        CarDTO carDTO = CarDTO.builder()
                .carNumber("12가3456").build();
        exitService.searchCar(carDTO);
    }
}