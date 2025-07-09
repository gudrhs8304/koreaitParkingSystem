package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.vo.ParkingLogVO;
import com.koreait.koreaitparkingsystem.dao.ParkingLogDAO;
import com.koreait.koreaitparkingsystem.dao.ParkingSpotDAO;
import com.koreait.koreaitparkingsystem.dto.CarDTO;
import com.koreait.koreaitparkingsystem.dto.ParkingLogDTO;
import com.koreait.koreaitparkingsystem.util.MapperUtil;
import org.modelmapper.ModelMapper;
import java.time.LocalDateTime;

public enum EntryService {
    INSTANCE;

    private final CarService carService = CarService.INSTANCE;
    private final ParkingSpotDAO spotDAO = ParkingSpotDAO.INSTANCE;
    private final ParkingLogDAO logDAO = ParkingLogDAO.INSTANCE;
    private final ModelMapper modelMapper = MapperUtil.INSTANCE.getInstance();

    public void processEntry(CarDTO carDTO) {

        // ✅ 이미 활성화된 입차 로그가 있는지 확인
        ParkingLogDTO activeLog = ParkingLogService.INSTANCE.getActiveLogByCarNumber(carDTO.getCarNumber());
        if (activeLog != null && activeLog.getOutTime() == null) {
            throw new RuntimeException("이미 입차 처리된 차량입니다. 출차 후 다시 등록해주세요.");
        }

        // ✅ 차량 등록 여부 확인 및 Car table 추가 처리.
        if (!carService.isRegistered(carDTO.getCarNumber())) {
            carService.addCar(carDTO);
        }

        // ✅ 빈 자리 배정
        Integer spot = spotDAO.assignSpot();
        if (spot == null) {
            throw new RuntimeException("현재 빈 주차 공간이 없습니다. 입차할 수 없습니다.");
        }

        // ✅ DTO 생성
        ParkingLogDTO logDTO = ParkingLogDTO.builder()
                .carNumber(carDTO.getCarNumber())
                .carTypeCode(carDTO.getCarTypeCode())
                .parkingSpot(spot)
                .inTime(LocalDateTime.now())
                .build();

        // ✅ DB 저장
        ParkingLogVO logVO = modelMapper.map(logDTO, ParkingLogVO.class);
        logDAO.insertEntry(logVO);

        // ✅ 자리 점유 처리
        spotDAO.occupySpot(spot);
    }
}
