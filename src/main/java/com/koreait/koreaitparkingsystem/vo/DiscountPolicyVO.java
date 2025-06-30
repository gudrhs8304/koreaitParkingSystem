package com.koreait.koreaitparkingsystem.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountPolicyVO {
    String car_type_code;
    int discount_rate;
}
