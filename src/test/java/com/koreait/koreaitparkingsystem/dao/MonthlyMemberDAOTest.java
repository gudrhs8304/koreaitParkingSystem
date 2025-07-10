package com.koreait.koreaitparkingsystem.dao;

import com.koreait.koreaitparkingsystem.vo.MonthlyMemberVO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MonthlyMemberDAOTest {

    MonthlyMemberDAO monthlyMemberDAO;

    @Test
    void insertMember() {
        MonthlyMemberVO member = MonthlyMemberVO.builder()
                .carNumber("assadasd")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(1))
                .driverName("asdsad")
                .phone("asdasd")
                .build();

        monthlyMemberDAO.insertMember(member);
    }
}