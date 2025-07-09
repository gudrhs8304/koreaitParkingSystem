package com.koreait.koreaitparkingsystem.dao;

import com.koreait.koreaitparkingsystem.vo.PricingPolicyVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Log4j2
public class PricingPolicyDAOTest {
    PricingPolicyDAO pricingPolicyDAO = PricingPolicyDAO.INSTANCE;

    @Test
    void selectPriceById() {
        int priceId = 1;
        int id = 2;
        String name = "name";
        pricingPolicyDAO.selectPriceById(id);
        log.info(pricingPolicyDAO);
    }

    @Test
    void updatePriceById() {
        int price = 2000;
        int id = 1;
        String name = "name";
        pricingPolicyDAO.updateFeeById(price,id);
        log.info(pricingPolicyDAO);
    }

    @Test
    void selectDailyMaxFeeTest() {
        int dailyMaxFee = pricingPolicyDAO.selectDailyMaxFee();
        log.info("🔍 DailyMaxFee from DB: {}", dailyMaxFee);

        // DB 값이 0보다 큰지 확인 (기본적으로 최대요금은 0일 수 없으니)
        assertTrue(dailyMaxFee > 0, "일일최대요금은 0보다 커야 합니다.");
    }

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
