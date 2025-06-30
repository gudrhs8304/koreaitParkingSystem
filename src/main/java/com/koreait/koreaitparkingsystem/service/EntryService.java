package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.dao.ParkingLogDAO;
import com.koreait.koreaitparkingsystem.dao.ParkingSpotDAO;
import com.koreait.koreaitparkingsystem.dto.CarDTO;
import com.koreait.koreaitparkingsystem.util.MapperUtil;
import com.koreait.koreaitparkingsystem.vo.ParkingLogVO;
import org.modelmapper.ModelMapper;

public class EntryService {

    private final CarService carService = CarService.INSTANCE;
    private final ParkingSpotDAO spotDAO = new ParkingSpotDAO();
    private final ParkingLogDAO logDAO = new ParkingLogDAO();
    private final ModelMapper modelMapper = MapperUtil.INSTANCE.getInstance();

    public void processEntry(CarDTO carDTO) {
        if (!carService.isRegistered(carDTO.getCarNumber())) {
            carService.addCar(carDTO);
        }

        Integer spot = spotDAO.assignSpot();
        if (spot == null) {
            throw new RuntimeException("빈 주차 공간이 없습니다.");
        }

        logDAO.insertEntry(ParkingLogVO.builder()
                .carNumber(carDTO.getCarNumber())
                .carTypeCode(carDTO.getCarTypeCode())
                .parkingSpot(spot)
                .build());
    }
}
