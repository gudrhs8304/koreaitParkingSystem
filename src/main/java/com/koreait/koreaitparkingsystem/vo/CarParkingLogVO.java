package com.koreait.koreaitparkingsystem.vo;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CarParkingLogVO {
    private int id;
    private String carNumber;
    private String carTypeCode;
    private int parkingSpot;
    private LocalDateTime inTime;
    private LocalDateTime outTime;
    private int fee;
}