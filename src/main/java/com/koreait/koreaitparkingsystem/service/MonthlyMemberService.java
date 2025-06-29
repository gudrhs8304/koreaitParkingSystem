package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.dao.MonthlyMemberDAO;
import com.koreait.koreaitparkingsystem.vo.MonthlyMemberVO;

import java.util.List;

public enum MonthlyMemberService {
    INSTANCE;

    private final MonthlyMemberDAO monthlyMemberDAO;
    
    MonthlyMemberService() {
        monthlyMemberDAO = new MonthlyMemberDAO();
    }
    
    public List<MonthlyMemberVO> getMonthlyMembers() {
        return monthlyMemberDAO.selectMonthlyMembers();
    }
    
}
