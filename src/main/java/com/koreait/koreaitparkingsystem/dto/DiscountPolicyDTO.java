package com.koreait.koreaitparkingsystem.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DiscountPolicyDTO {

    private String carTypeCode;
    private int discountRate;
}
