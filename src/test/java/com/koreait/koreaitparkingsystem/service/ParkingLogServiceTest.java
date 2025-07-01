package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.dto.CarDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLogServiceTest {
    ParkingLogService parkingLogService = ParkingLogService.INSTANCE;

    @Test
    void updateParkingLog() {
        CarDTO carDTO = CarDTO.builder()
                .carNumber("56다1234").build();
        parkingLogService.updateParkingLog(carDTO);
    }

}