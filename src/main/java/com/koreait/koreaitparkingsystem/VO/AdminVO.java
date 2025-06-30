package com.koreait.koreaitparkingsystem.VO;

import lombok.*;
import org.checkerframework.checker.units.qual.C;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminVO {
    private String username;
    private String password;

}
