package com.koreait.koreaitparkingsystem.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PricingPolicyVO {

  private int id;
  private String name;
  private int price;

  private int durationMinutes;
  private boolean isAdditional;
  private boolean isDailyMax;
}
