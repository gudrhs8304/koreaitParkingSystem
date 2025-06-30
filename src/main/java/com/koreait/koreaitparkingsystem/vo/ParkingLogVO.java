package com.koreait.koreaitparkingsystem.vo;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParkingLogVO {

    private int id;
    private String carNumber;
    private String carTypeCode;
    private int parkingSpot;
    private LocalDateTime inTime;
    private LocalDateTime outTime;
    private int fee;
}
