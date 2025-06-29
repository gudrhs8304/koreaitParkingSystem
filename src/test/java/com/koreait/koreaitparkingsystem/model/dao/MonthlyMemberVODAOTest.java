package com.koreait.koreaitparkingsystem.model.dao;

import com.koreait.koreaitparkingsystem.dao.MonthlyMemberDAO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

@Log4j2
class MonthlyMemberVODAOTest {

    private final MonthlyMemberDAO monthlyMemberDAO = new MonthlyMemberDAO();

    @Test
    void selectMonthlyMember() {
        monthlyMemberDAO.selectMonthlyMembers().forEach(System.out::println);
    }
}