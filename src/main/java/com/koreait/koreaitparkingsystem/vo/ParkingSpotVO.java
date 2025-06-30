package com.koreait.koreaitparkingsystem.VO;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSpotVO {

    private int spotNumber;
    private boolean isOccupied;
}
