package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.dao.*;
import com.koreait.koreaitparkingsystem.dto.CarDTO;
import com.koreait.koreaitparkingsystem.dto.ParkingLogDTO;
import com.koreait.koreaitparkingsystem.util.MapperUtil;
import com.koreait.koreaitparkingsystem.vo.ParkingLogVO;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

@Log4j2
public enum TotalFeeService {
    INSTANCE;

    private final ParkingSpotDAO parkingSpotDAO =  ParkingSpotDAO.INSTANCE;
    private final ParkingLogDAO parkingLogDAO  =  ParkingLogDAO.INSTANCE;
    private final CarService carService  =  CarService.INSTANCE;
    private final ModelMapper modelMapper = MapperUtil.INSTANCE.getInstance();
    private final CarTypeDAO carTypeDAO = CarTypeDAO.INSTANCE;
    private final DiscountPolicyDAO discountPolicyDAO = DiscountPolicyDAO.INSTANCE;
    private final MonthlyMemberService monthlyMemberService =  MonthlyMemberService.INSTANCE;
    private final CarDAO carDAO = CarDAO.INSTANCE;

    public void printTotalFee(CarDTO carDTO) {
        // 출차된 parking_log 중 가장 최근 기록 가져오기
        ParkingLogVO logVO = parkingLogDAO.selectLastLogByCarNumber(carDTO.getCarNumber());
        ParkingLogDTO logDTO = null;
        if (logVO != null) {
            logDTO = modelMapper.map(logVO, ParkingLogDTO.class);
        }

        if (logDTO != null) {
            int originalFee = logDTO.getFee();
            String carTypeCode = logDTO.getCarTypeCode();
            int discountRate = discountPolicyDAO.selectDiscountRate(carTypeCode);

            int discountedFee = originalFee;
            if (discountRate > 0) {
                discountedFee = originalFee - (originalFee * discountRate / 100);
            }

            log.info("원래 요금: " + originalFee);
            log.info("할인율(" + discountRate + "%) 적용된 최종 요금: " + discountedFee);
        } else {
            log.info("주차 로그를 찾을 수 없습니다.");
        }
    }

    public int calculateFee(ParkingLogDTO logDTO) {
        if (logDTO == null || logDTO.getInTime() == null) {
            return 0; // 안전: 계산 못하면 기본 0원
        }

        java.sql.Timestamp outTime = java.sql.Timestamp.valueOf(java.time.LocalDateTime.now());
        java.sql.Timestamp inTime = java.sql.Timestamp.valueOf(logDTO.getInTime());

        long durationMillis = outTime.getTime() - inTime.getTime();
        long durationMinutes = durationMillis / (1000 * 60);
        if (durationMinutes < 30) durationMinutes = 30;

        double baseRatePer30Min = 3000.0;
        double fee = (durationMinutes / 30.0) * baseRatePer30Min;

        return (int) fee;
    }

    public int calculateDiscountAmount(int originalFee, int discountRate) {
        return (originalFee * discountRate) / 100;
    }
}
