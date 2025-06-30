package com.koreait.koreaitparkingsystem.vo;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class MonthlyMemberVO {

    private String carNumber;
    private String driverName;
    private String phone;
    private LocalDate startDate;
    private LocalDate endDate;
}
