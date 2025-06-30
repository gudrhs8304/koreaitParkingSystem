package com.koreait.koreaitparkingsystem.vo;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DiscountPolicyVO {

    private String carTypeCode;
    private int discountRate;
}
