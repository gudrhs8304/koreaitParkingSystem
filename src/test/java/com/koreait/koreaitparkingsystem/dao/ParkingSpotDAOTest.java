package com.koreait.koreaitparkingsystem.dao;

import com.koreait.koreaitparkingsystem.VO.ParkingSpotVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.util.List;

@Log4j2
public class ParkingSpotDAOTest {

    @Test
    void findAll() {
        ParkingSpotDAO parkingSpotDAO = new ParkingSpotDAO();
        List<ParkingSpotVO> parkingSpots = parkingSpotDAO.findAll();
        if (parkingSpots == null) {
            log.info(("주자 공간 목록 불러오기 실패."));
            return;
        }
        if (parkingSpots.size() == 0) {
            log.info(("주차 공간이 없습니다.."));
        } else {
            log.info(("주차 공간 불러오기 성공 ! 전채 개수 : " + parkingSpots.size()));
            for (ParkingSpotVO parkingSpot : parkingSpots) {
                log.info(("번호 : " + parkingSpot.getSpot_number()
                        + ", 사용 여부 : " + parkingSpot.is_occupied()));
            }
        }


    }

    @Test
    void findById() {
        ParkingSpotDAO parkingSpotDAO = new ParkingSpotDAO();
        int spot_number = 1;
        ParkingSpotVO parkingSpotVO = parkingSpotDAO.findById(spot_number);
        if (parkingSpotVO == null) {
            log.info((spot_number + "번을 찾을 수 없습니다."));
        } else {
            log.info((spot_number + "번 자리 찾기 성공 !  번호 : " + parkingSpotVO.getSpot_number()
                    + ", 사용 여부 : " + parkingSpotVO.is_occupied()));

        }
    }

    @Test
    void updateParkingSpot() {
        ParkingSpotDAO parkingSpotDAO = new ParkingSpotDAO();
        int spot_number = 1;
        ParkingSpotVO parkingSpotVO = parkingSpotDAO.findById(spot_number);
        boolean is_occupied = parkingSpotVO.is_occupied();
        parkingSpotDAO.updateParkingSpot(spot_number, is_occupied);
        ParkingSpotVO parkingSpotVO2 = parkingSpotDAO.findById(spot_number);
        if (parkingSpotVO2.is_occupied() == is_occupied) {
            log.info(spot_number + "번 자리 상태 변경 성공 : " + parkingSpotVO2.getSpot_number());
        } else {
            log.info(spot_number + "번 자리 상태 변경 실패");
        }
    }
}