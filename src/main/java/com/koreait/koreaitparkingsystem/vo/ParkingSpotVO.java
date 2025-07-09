package com.koreait.koreaitparkingsystem.vo;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSpotVO {

    private int spotNumber;
    private boolean isOccupied;
    private String carNumber;
}
