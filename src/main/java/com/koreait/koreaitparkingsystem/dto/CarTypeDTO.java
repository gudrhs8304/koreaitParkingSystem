package com.koreait.koreaitparkingsystem.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class CarTypeDTO {

    private String code;
    private String name;
}
