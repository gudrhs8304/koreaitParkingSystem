package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.dao.ParkingLogDAO;
import com.koreait.koreaitparkingsystem.dao.ParkingSpotDAO;
import com.koreait.koreaitparkingsystem.dto.CarDTO;
import com.koreait.koreaitparkingsystem.dto.ParkingLogDTO;
import com.koreait.koreaitparkingsystem.dto.ParkingSpotDTO;
import com.koreait.koreaitparkingsystem.util.MapperUtil;
import com.koreait.koreaitparkingsystem.vo.ParkingLogVO;
import com.koreait.koreaitparkingsystem.vo.ParkingSpotVO;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

@Log4j2
public enum ParkingSpotService {
    INSTANCE;

    private final ParkingSpotDAO parkingSpotDAO = ParkingSpotDAO.INSTANCE;
    private final ModelMapper modelMapper = MapperUtil.INSTANCE.getInstance();
    private final ParkingLogDAO parkingLogDAO = ParkingLogDAO.INSTANCE;


    public List<ParkingSpotDTO> getParkingSpots() {

        // 1. vo 타입으로 선언된 parkingSpotVOS 를 dao의 메서드로 사용해 가져오기
        List<ParkingSpotVO> parkingSpotVOS = parkingSpotDAO.selectAllParkingSpots();

        // 2. 맵퍼가 정확하게 타입 정보를 알 수 있도록 정보 제시. TypeToken 사용.
        Type listType = new TypeToken<List<ParkingSpotDTO>>() {}.getType();
        return modelMapper.map(parkingSpotVOS, listType); // 값을 반환 인라인화.
    }

    public void isParkingSpot(CarDTO carDTO) {
        ParkingLogVO logVO = parkingLogDAO.selectLastLogByCarNumber(carDTO.getCarNumber());
        ParkingLogDTO logDTO = null;
        if (logVO != null) {
            logDTO = modelMapper.map(logVO, ParkingLogDTO.class);
        }

        if (logDTO != null && logDTO.getOutTime() != null) {
            int spotNumber = logDTO.getParkingSpot();
            parkingSpotDAO.releaseSpot(spotNumber);
            log.info("주차 구역 " + spotNumber + "이(가) 출차되어 사용 가능 상태로 변경되었습니다.");
        } else {
            log.info("출차 로그를 찾을 수 없거나 아직 출차되지 않았습니다.");
        }
    }
}
