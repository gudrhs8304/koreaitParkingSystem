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

    // jstl 호환성 문제로 메서드 재정의. 오버라이드는 아님.
    public boolean getIsOccupied() {
        return isOccupied;
    }
    public boolean isOccupied() {
        return isOccupied;
    }
}
