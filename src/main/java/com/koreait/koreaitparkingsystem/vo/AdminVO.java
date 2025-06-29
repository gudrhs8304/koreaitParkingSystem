package com.koreait.koreaitparkingsystem.vo;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class AdminVO {
    private String username;
    private String password;
}
