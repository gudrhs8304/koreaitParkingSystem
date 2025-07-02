package com.koreait.koreaitparkingsystem.vo;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CarStatusVO {
    private String carNumber;
    private String driverName;
    private String phone;
}
