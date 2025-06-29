package com.koreait.koreaitparkingsystem.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
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
