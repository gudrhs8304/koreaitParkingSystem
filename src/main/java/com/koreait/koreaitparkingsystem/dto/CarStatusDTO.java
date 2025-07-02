package com.koreait.koreaitparkingsystem.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CarStatusDTO {
    private String carNumber;
    private String carTypeCode;
    private String driverName;
    private String phone;
    private int parkedHours; // 새로 추가
    private boolean over12Hours; // 새로 추가
    private String over12HoursIcon; // 새로 추가 (아이콘)
    private LocalDateTime inTime; // 새로 추가
    private String carTypeName; // 새로 추가

    public String getInTimeFormatted() { // 새로 추가
        if (inTime == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM월 dd일 HH시 mm분");
        return inTime.format(formatter);
    }

}
