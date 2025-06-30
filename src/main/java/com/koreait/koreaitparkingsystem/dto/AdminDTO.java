package com.koreait.koreaitparkingsystem.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class AdminDTO {
    private String username;
    private String password;
}
