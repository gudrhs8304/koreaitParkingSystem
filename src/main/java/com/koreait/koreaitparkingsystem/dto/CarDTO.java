package com.koreait.koreaitparkingsystem.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CarDTO {

    private String carNumber;
    private String carTypeCode;
    private String driverName;
    private String phone;
}
