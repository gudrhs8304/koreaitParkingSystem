package com.koreait.koreaitparkingsystem.vo;


import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CarVO {

    private String carNumber;
    private String carTypeCode;
    private String driverName;
    private String phone;
}
