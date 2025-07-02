package com.koreait.koreaitparkingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParkingLogDTO {
    private int id;
    private String carNumber;
    private String carTypeCode;
    private int parkingSpot;
    private LocalDateTime inTime;
    private LocalDateTime outTime;
    private int fee;
}
