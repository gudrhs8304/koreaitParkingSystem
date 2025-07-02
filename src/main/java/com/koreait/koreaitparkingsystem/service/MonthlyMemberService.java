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

    public MonthlyMemberVO getMonthlyMember(String carNumber) {
        return monthlyMemberDAO.selectByCarNumber(carNumber);
    }
    public boolean isValidMonthlyMember(String carNumber) {
        return monthlyMemberDAO.isValidMember(carNumber);
    }

    public void addMonthlyMember(MonthlyMemberVO monthlyMemberVO) {
        monthlyMemberDAO.insertMember(monthlyMemberVO);
    }
    public void editMonthlyMember(MonthlyMemberVO monthlyMemberVO) {
        monthlyMemberDAO.updateMember(monthlyMemberVO);
    }
    public void removeMonthlyMember(String cardNumber) {
        monthlyMemberDAO.deleteMemberByCarNumber(cardNumber);
    }
}
