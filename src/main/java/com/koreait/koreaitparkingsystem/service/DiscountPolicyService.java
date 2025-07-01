package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.dao.DiscountPolicyDAO;
import com.koreait.koreaitparkingsystem.vo.DiscountPolicyVO;

import java.util.List;

public enum DiscountPolicyService {
    INSTANCE;

    DiscountPolicyDAO discountPolicyDAO = DiscountPolicyDAO.INSTANCE;
    public void editDiscountRate(String code, int rate) {
        discountPolicyDAO.updateDiscountRate(code, rate);
    }
    public List<DiscountPolicyVO> getAllDiscounts() {
        return discountPolicyDAO.selectAll(); // DAO에서 전체 할인 정보 불러오는 메서드
    }
}
