package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.dao.ParkingLogDAO;
import com.koreait.koreaitparkingsystem.dao.ParkingSpotDAO;
import com.koreait.koreaitparkingsystem.dto.CarDTO;
import com.koreait.koreaitparkingsystem.dto.ParkingLogDTO;
import com.koreait.koreaitparkingsystem.util.MapperUtil;
import com.koreait.koreaitparkingsystem.vo.ParkingLogVO;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

public class EntryService {

    private final CarService carService = CarService.INSTANCE;
    private final ParkingSpotDAO spotDAO = new ParkingSpotDAO();
    private final ParkingLogDAO logDAO = new ParkingLogDAO();
    private final ModelMapper modelMapper = MapperUtil.INSTANCE.getInstance();

    public void processEntry(CarDTO carDTO) {
        // 1. 차량 등록 여부 확인
        if (!carService.isRegistered(carDTO.getCarNumber())) {
            carService.addCar(carDTO);
        }

        // 2. 빈 자리 배정
        Integer spot = spotDAO.assignSpot();
        if (spot == null) {
            throw new RuntimeException("현재 빈 주차 공간이 없습니다. 입차할 수 없습니다.");
        }

        // 3. DTO 생성
        ParkingLogDTO logDTO = ParkingLogDTO.builder()
                .carNumber(carDTO.getCarNumber())
                .carTypeCode(carDTO.getCarTypeCode())
                .parkingSpot(spot)
                .inTime(LocalDateTime.now())
                .build();

        // 4. DTO → VO 변환 후 DB 저장
        ParkingLogVO logVO = modelMapper.map(logDTO, ParkingLogVO.class);
        logDAO.insertEntry(logVO);

        // 5. 주차 공간 상태 업데이트
        spotDAO.occupySpot(spot);
    }
}
