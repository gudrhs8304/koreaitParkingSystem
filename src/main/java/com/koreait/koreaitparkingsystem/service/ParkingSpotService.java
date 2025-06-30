package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.dao.ParkingSpotDAO;
import com.koreait.koreaitparkingsystem.dto.ParkingSpotDTO;
import com.koreait.koreaitparkingsystem.util.MapperUtil;
import com.koreait.koreaitparkingsystem.vo.ParkingSpotVO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public enum ParkingSpotService {
    INSTANCE;

    private final ParkingSpotDAO parkingSpotDAO = ParkingSpotDAO.INSTANCE;
    private final ModelMapper modelMapper = MapperUtil.INSTANCE.getInstance();


    public List<ParkingSpotDTO> getParkingSpots() {

        // 1. vo 타입으로 선언된 parkingSpotVOS 를 dao의 메서드로 사용해 가져오기
        List<ParkingSpotVO> parkingSpotVOS = parkingSpotDAO.selectAllParkingSpots();

        // 2. 맵퍼가 정확하게 타입 정보를 알 수 있도록 정보 제시. TypeToken 사용.
        Type listType = new TypeToken<List<ParkingSpotDTO>>() {}.getType();
        return modelMapper.map(parkingSpotVOS, listType); // 값을 반환 인라인화.
    }
}
