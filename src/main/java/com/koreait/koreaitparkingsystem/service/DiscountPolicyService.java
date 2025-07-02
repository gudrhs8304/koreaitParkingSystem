package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.dao.*;
import com.koreait.koreaitparkingsystem.dto.CarDTO;
import com.koreait.koreaitparkingsystem.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

@Log4j2
public enum DiscountPolicyService {
    INSTANCE;

    private final ParkingSpotDAO parkingSpotDAO =  ParkingSpotDAO.INSTANCE;
    private final ParkingLogDAO parkingLogDAO  =  ParkingLogDAO.INSTANCE;
    private static final CarService carService  =  CarService.INSTANCE;
    private final ModelMapper modelMapper = MapperUtil.INSTANCE.getInstance();
    private final CarTypeDAO carTypeDAO = CarTypeDAO.INSTANCE;
    private static final DiscountPolicyDAO discountPolicyDAO = DiscountPolicyDAO.INSTANCE;
    private final MonthlyMemberService monthlyMemberService =  MonthlyMemberService.INSTANCE;
    private final CarDAO carDAO = CarDAO.INSTANCE;



    // 차량 정보 기반으로 할인 정책 확인 후 로그 출력
    public static void printDiscountPolicy(CarDTO carDTO) {
        CarDTO result = carService.getCar(carDTO);
        if (result != null) {
            String carTypeCode = result.getCarTypeCode();
            int discountRate = discountPolicyDAO.selectDiscountRate(carTypeCode);
            if (discountRate > 0) {
                log.info("DiscountPolicyService 31 할인율: " + discountRate);
            } else {
                log.info("DiscountPolicyService 33 해당 차종에 대한 할인 정책이 없습니다.");
            }
        } else {
            log.info("DiscountPolicyService 36 해당 차량이 존재하지 않습니다.");
        }
    }

    // 차종 코드로 할인율 조회 (출차 계산 등에 활용)
    public int getDiscountRateByTypeCode(String carTypeCode) {
        return discountPolicyDAO.selectDiscountRate(carTypeCode);
    }
}
