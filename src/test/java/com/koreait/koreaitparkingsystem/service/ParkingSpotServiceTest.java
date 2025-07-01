package com.koreait.koreaitparkingsystem.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingSpotServiceTest {

    ParkingSpotService parkingSpotService = ParkingSpotService.INSTANCE;

    @Test
    void getParkingSpot() {
        parkingSpotService.getParkingSpots().forEach(System.out::println);
    }
}