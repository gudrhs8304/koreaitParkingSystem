package com.koreait.koreaitparkingsystem.dao;

import com.koreait.koreaitparkingsystem.vo.ParkingLogVO;
import org.junit.jupiter.api.Test;


class ParkingLogDAOTest {
    ParkingLogDAO dao = ParkingLogDAO.INSTANCE;

    @Test
    void insertEntry() {
        ParkingLogVO parkingLogVO = ParkingLogVO.builder()
                .carNumber("123456")
                .carTypeCode("normal")
                .parkingSpot(4)
                .build();
        dao.insertEntry(parkingLogVO);
    }

    @Test
    void updateExit() {
    }

    @Test
    void selectActiveLogByCarNumber() {
    }

    @Test
    void selectLongTermParked() {
    }
}