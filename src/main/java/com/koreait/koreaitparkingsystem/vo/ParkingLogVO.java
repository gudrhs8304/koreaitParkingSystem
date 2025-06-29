package com.koreait.koreaitparkingsystem.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParkingLogVO {

    private int id;
    private String carNumber;
    private String carTypeCode;
    private String parkingSpot;
    private LocalDateTime inTime;
    private LocalDateTime outTime;
    private int fee;
}
