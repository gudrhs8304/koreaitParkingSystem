package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.dao.ParkingLogDAO;
import com.koreait.koreaitparkingsystem.dao.ParkingSpotDAO;
import com.koreait.koreaitparkingsystem.vo.CarVO;
import com.koreait.koreaitparkingsystem.vo.ParkingLogVO;

public class EntryService {

    private final CarService carService = CarService.INSTANCE;
    private final ParkingSpotDAO spotDAO = new ParkingSpotDAO();
    private final ParkingLogDAO logDAO = new ParkingLogDAO();

    public void processEntry(CarVO carVO) {
        if (!carService.isRegistered(carVO.getCarNumber())) {
            carService.addCar(carVO);
        }

        Integer spot = spotDAO.assignSpot();
        if (spot == null) {
            throw new RuntimeException("빈 주차 공간이 없습니다.");
        }

        logDAO.insertEntry(ParkingLogVO.builder()
                .carNumber(carVO.getCarNumber())
                .carTypeCode(carVO.getCarTypeCode())
                .parkingSpot(spot)
                .build());
    }
}
