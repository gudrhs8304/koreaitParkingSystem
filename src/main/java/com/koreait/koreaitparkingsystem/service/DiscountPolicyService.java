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




    public static void printDiscountPolicy(CarDTO carDTO) {
        CarDTO result = carService.getCar(carDTO);
        if (result != null) {
            String carTypeCode = result.getCarTypeCode();
            int discountRate = discountPolicyDAO.selectDiscountRate(carTypeCode);
            if (discountRate > 0) {
                log.info("할인율: " + discountRate);
            } else {
                log.info("해당 차종에 대한 할인 정책이 없습니다.");
            }
        } else {
            log.info("해당 차량이 존재하지 않습니다.");
        }
    }

    public int getDiscountRateByTypeCode(String carTypeCode) {
        return discountPolicyDAO.selectDiscountRate(carTypeCode);
    }
}
