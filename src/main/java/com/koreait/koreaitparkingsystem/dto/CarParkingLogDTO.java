package com.koreait.koreaitparkingsystem.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CarParkingLogDTO {
    private int id;
    private String carNumber;
    private String carTypeCode;
    private int parkingSpot;
    private LocalDateTime inTime;
    private LocalDateTime outTime;
    private int fee;


}
