package com.koreait.koreaitparkingsystem.dao;

import com.koreait.koreaitparkingsystem.vo.CarVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

@Log4j2
class CarDAOTest {

    private final static CarDAO carDAO = CarDAO.INSTANCE;

    @Test
    void selectCarByNum() {
        System.out.println(carDAO.selectCarByNum("12가3456"));
    }

    @Test
    void insertCar() {
        CarVO carVO = CarVO.builder()
                .carNumber("10가1239")
                .carTypeCode("electric")
                .driverName("냐옹")
                .phone("123")
                .build();
        carDAO.insertCar(carVO);
    }

    @Test
    void isRegistered() {
        System.out.println(((carDAO.selectIsRegistered("12가3456") ? "있음" : "없음")));
    }
}