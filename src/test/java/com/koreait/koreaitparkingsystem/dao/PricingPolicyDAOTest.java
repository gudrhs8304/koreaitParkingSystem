package com.koreait.koreaitparkingsystem.dao;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import com.koreait.koreaitparkingsystem.vo.ParkingSpotVO;

import static org.junit.jupiter.api.Assertions.*;
@Log4j2
class PricingPolicyDAOTest {
    PricingPolicyDAO pricingPolicyDAO = new PricingPolicyDAO();


    @Test
    void updatePolicyPrice() {
        // Given: 테스트용 요금 정책 데이터
        int id = 1;
        int newPrice = 5000;

        PricingPolicyVO pricingPolicyVO = new PricingPolicyVO();
        vo.setId(id);
        vo.setPrice(newPrice);

        // When: 가격 업데이트 실행
        pricingPolicyDAO.updatePolicyPrice(vo);

        // Then: 로그로 확인 (DB에 반영 여부는 콘솔에서 확인)
        log.info("업데이트 실행됨 - ID: {}, 변경된 가격: {}", vo.getId(), vo.getPrice());

    }
}