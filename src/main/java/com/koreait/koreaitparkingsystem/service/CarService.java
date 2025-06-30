package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.vo.CarVO;
import com.koreait.koreaitparkingsystem.dao.CarDAO;

import com.koreait.koreaitparkingsystem.dto.CarDTO;
import com.koreait.koreaitparkingsystem.util.MapperUtil;


import org.modelmapper.ModelMapper;

public enum CarService {
    INSTANCE;

    private final CarDAO carDAO;
    private final ModelMapper modelMapper;

    CarService() {
        carDAO = new CarDAO();
        modelMapper = MapperUtil.INSTANCE.getInstance();
    }

    // 차량 정보 조회 (DTO 입력 → DTO 반환)
    public CarDTO getCar(CarDTO dto) {
        CarVO vo = carDAO.selectCarByNum(dto.getCarNumber());
        return vo != null ? modelMapper.map(vo, CarDTO.class) : null;
    }

    // 차량 등록 (DTO 입력)
    public void addCar(CarDTO dto) {
        CarVO vo = modelMapper.map(dto, CarVO.class);
        carDAO.insertCar(vo);
    }

    // 등록 여부 확인 (문자열 입력 → boolean 반환 , 단순조회이므로 맵퍼 미사용.)
    public boolean isRegistered(String carNumber) {
        return carDAO.selectIsRegistered(carNumber);
    }
}
