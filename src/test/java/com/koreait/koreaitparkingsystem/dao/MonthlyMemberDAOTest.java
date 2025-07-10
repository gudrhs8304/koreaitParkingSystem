package com.koreait.koreaitparkingsystem.dao;

import com.koreait.koreaitparkingsystem.vo.MonthlyMemberVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

@Log4j2
class MonthlyMemberDAOTest {

    private final MonthlyMemberDAO monthlyMemberDAO = MonthlyMemberDAO.INSTANCE;

    @Test
    void insertMember() {

        MonthlyMemberVO member = MonthlyMemberVO.builder()
                .carNumber("assadasd")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(1))
                .driverName("asdsad")
                .phone("asdasd")
                .build();
        log.info(member.toString());

        monthlyMemberDAO.insertMember(member);
    }

    @Test
    void updateMember() {
        MonthlyMemberVO member = MonthlyMemberVO.builder()
                .carNumber("assadasd")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(1))
                .driverName("김철수박")
                .phone("010-1234-5555")
                .build();
        monthlyMemberDAO.updateMember(member);
    }
}