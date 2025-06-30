package com.koreait.koreaitparkingsystem.VO;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSpotVO {
    private int spot_number; // 주차 공간 번호
    private boolean is_occupied; // 사용 중 여부
}
