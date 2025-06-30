package com.koreait.koreaitparkingsystem.dao;

import com.koreait.koreaitparkingsystem.vo.PricingPolicyVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

@Log4j2
public class PricingPolicyDAOTest {
    PricingPolicyDAO pricingPolicyDAO = new PricingPolicyDAO();

//    @Test
//    public void updateIsAdditional() {
//        String name = "기본요금2";
//        int price = 3000;
//        int duration_minutes = 30;
//        boolean is_additional = true;
//        boolean is_daily_max = false;
//        int id = 1;
//        PricingPolicyVO pricingPolicyVO = PricingPolicyVO.builder()
//                .name(name)
//                .price(price)
//                .duration_minutes(duration_minutes)
//                .is_additional(is_additional)
//                .is_daily_max(is_daily_max)
//                .id(id).build();
//        pricingPolicyDAO.updateIsAdditional(pricingPolicyVO);
//        log.info(pricingPolicyVO);

//    }
}
