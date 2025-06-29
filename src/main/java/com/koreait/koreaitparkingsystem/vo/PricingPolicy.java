package com.koreait.koreaitparkingsystem.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PricingPolicy {

  private int id;
  private String name;
  private int durationMinutes;
  private boolean isAdditional;
  private boolean isDailyMax;
}
