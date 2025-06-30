package com.koreait.koreaitparkingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSpotDTO {

    private int spotNumber;
    private boolean isOccupied;
}
