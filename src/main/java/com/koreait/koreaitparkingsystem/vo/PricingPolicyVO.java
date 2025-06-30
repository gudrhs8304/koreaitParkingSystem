package com.koreait.koreaitparkingsystem.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.checkerframework.checker.units.qual.N;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PricingPolicyVO {
    Integer id;
    String name;
    int price;
    int discount_rate;
    int duration_minutes;
    boolean is_additional;
    boolean is_daily_max;
}
