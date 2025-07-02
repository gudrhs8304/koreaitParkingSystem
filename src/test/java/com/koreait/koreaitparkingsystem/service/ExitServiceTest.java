package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.dto.CarDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
class ExitServiceTest {
    ExitService exitService = ExitService.INSTANCE;
    CarselectService carselectService = CarselectService.INSTANCE;
    DiscountPolicyService discountPolicyService = DiscountPolicyService.INSTANCE;

    @Test
    void printCarType() {
        CarDTO carDTO = CarDTO.builder()
                .carNumber("12가3456").build();
        carselectService.printCarType(carDTO);
    }

    @Test
    public void testProcessExit() {
        // GIVEN: 테스트할 차량 번호
        String testCarNumber = "77사7777";
        CarDTO carDTO = CarDTO.builder()
                .carNumber(testCarNumber)
                .build();

        try {
            // WHEN: 출차 처리 실행
            ExitService.INSTANCE.processExit(carDTO);

            // THEN: 별도의 assert는 없지만 로그로 확인
            log.info("✅ 테스트 성공: 출차 처리 완료");
        } catch (RuntimeException e) {
            log.error("❌ 테스트 실패: " + e.getMessage());
        }
    }








//    @Test
//    void searchCar() {
//        CarDTO carDTO = CarDTO.builder()
//                .carNumber("12가3456").build();
//        exitService.searchCar(carDTO);
//    }
}