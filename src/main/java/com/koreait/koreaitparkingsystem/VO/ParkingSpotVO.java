package com.koreait.koreaitparkingsystem.VO;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSpotVO {
    String spot_number;
    String is_occupied;
}
