package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.dto.CarDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingSpotServiceTest {

    ParkingSpotService parkingSpotService = ParkingSpotService.INSTANCE;

    @Test
    void getParkingSpot() {
        parkingSpotService.getParkingSpots().forEach(System.out::println);
    }

    @Test
    void isParkingSpot() {
        CarDTO carDTO = CarDTO.builder()
                .carNumber("88바8888").build();
        parkingSpotService.isParkingSpot(carDTO);
    }
}