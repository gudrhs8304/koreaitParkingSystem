package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.dao.DiscountPolicyDAO;
import com.koreait.koreaitparkingsystem.vo.DiscountPolicyVO;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;

@Log4j2
public class DiscountPolicyService {
    DiscountPolicyDAO discountPolicyDAO =  new DiscountPolicyDAO();

    void getDiscountPolicy() {
        String  = 1;
        discountPolicyDAO.select(id)
    }
}
