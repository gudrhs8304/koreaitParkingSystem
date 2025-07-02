package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.dao.*;
import com.koreait.koreaitparkingsystem.dto.CarDTO;
import com.koreait.koreaitparkingsystem.dto.CarTypeDTO;
import com.koreait.koreaitparkingsystem.util.MapperUtil;
import com.koreait.koreaitparkingsystem.vo.CarTypeVO;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

@Log4j2
public enum CarselectService {
    INSTANCE;

    private final ParkingSpotDAO parkingSpotDAO =  ParkingSpotDAO.INSTANCE;
    private final ParkingLogDAO parkingLogDAO  =  ParkingLogDAO.INSTANCE;
    private final CarService carService  =  CarService.INSTANCE;
    private final ModelMapper modelMapper = MapperUtil.INSTANCE.getInstance();
    private final CarTypeDAO carTypeDAO = CarTypeDAO.INSTANCE;
    private final DiscountPolicyDAO discountPolicyDAO = DiscountPolicyDAO.INSTANCE;
    private final MonthlyMemberService monthlyMemberService =  MonthlyMemberService.INSTANCE;
    private final CarDAO carDAO = CarDAO.INSTANCE;

    public void printCar(CarDTO carDTO) {
        CarDTO result = carService.getCar(carDTO);
        if (result != null) {
            log.info("CarselectService 27 차량 타입: " + result.getCarTypeCode());
        } else {
            log.info("CarselectService 29 해당 차량이 존재하지 않습니다.");
        }
    }

    // 차량 정보 출력 (차량 타입 이름까지 출력)
    public void printCarType(CarDTO carDTO) {
        CarDTO result = carService.getCar(carDTO);
        if (result != null) {
            String carTypeCode = result.getCarTypeCode();
            CarTypeDTO carTypeDTO = null;

            // 모든 차량 타입 목록 조회 후 코드 비교
            for (CarTypeVO ctVO : carTypeDAO.selectCarType()) {
                CarTypeDTO ct = modelMapper.map(ctVO, CarTypeDTO.class);
                if (ct.getCode().equals(carTypeCode)) {
                    carTypeDTO = ct;
                    break;
                }
            }

            if (carTypeDTO != null) {
                log.info("CarselectService 48 차량 이름: " + carTypeDTO.getName());
            } else {
                log.info("CarselectService 50 차종 정보를 찾을 수 없습니다.");
            }
        } else {
            log.info("CarselectService 53 해당 차량이 존재하지 않습니다.");
        }
    }
}
