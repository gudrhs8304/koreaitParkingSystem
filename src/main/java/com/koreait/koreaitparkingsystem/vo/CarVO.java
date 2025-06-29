package com.koreait.koreaitparkingsystem.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CarVO {

    private String carNumber;
    private String carTypeCode;
    private String driverName;
    private String phone;
}
