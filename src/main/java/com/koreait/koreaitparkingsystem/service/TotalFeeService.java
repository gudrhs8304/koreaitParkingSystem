package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.dao.*;
import com.koreait.koreaitparkingsystem.dto.CarDTO;
import com.koreait.koreaitparkingsystem.dto.ParkingLogDTO;
import com.koreait.koreaitparkingsystem.util.MapperUtil;
import com.koreait.koreaitparkingsystem.vo.ParkingLogVO;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

/**
 * ✅ TotalFeeService
 * - 차량의 주차 요금/할인/최대요금 등 최종 정산 책임 서비스
 * - 입출차 로그/차량타입별 할인정책/최대요금정책/실시간 요금계산 기능 제공
 */

@Log4j2
public enum TotalFeeService {
    INSTANCE;

    // ... DAO, 서비스, 맵퍼 등 싱글톤 선언
    private final ParkingSpotDAO parkingSpotDAO =  ParkingSpotDAO.INSTANCE;
    private final ParkingLogDAO parkingLogDAO  =  ParkingLogDAO.INSTANCE;
    private final CarService carService  =  CarService.INSTANCE;
    private final ModelMapper modelMapper = MapperUtil.INSTANCE.getInstance();
    private final CarTypeDAO carTypeDAO = CarTypeDAO.INSTANCE;
    private final DiscountPolicyDAO discountPolicyDAO = DiscountPolicyDAO.INSTANCE;
    private final MonthlyMemberService monthlyMemberService =  MonthlyMemberService.INSTANCE;
    private final CarDAO carDAO = CarDAO.INSTANCE;
    private final  PricingPolicyDAO pricingPolicyDAO = PricingPolicyDAO.INSTANCE;

    /**
     * ✅ [출차 차량 요금 정보 출력]
     * - 차량번호로 마지막 출차 ParkingLog 조회
     * - 할인정책 적용 후 원/최종 요금 log 출력
     */
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

            log.info("TotalFeeService 42 원래 요금: " + originalFee);
            log.info("TotalFeeService 43 할인율(" + discountRate + "%) 적용된 최종 요금: " + discountedFee);
        } else {
            log.info("TotalFeeService 45 주차 로그를 찾을 수 없습니다.");
        }
    }

    /**
     * ✅ [주차 요금 계산]
     * - 입차 ~ 현재 시간 기준, 30분 단위로 기본/추가요금 산출
     * - 일일최대요금(24시간) 초과 시: 24시간 단위 최대요금 + 잔여시간 추가요금
     * - 요금정책은 PricingPolicyDAO에서 가져옴
     */
    public int calculateFee(ParkingLogDTO logDTO) {
        if (logDTO == null || logDTO.getInTime() == null) {
            return 0; // 안전: 계산 못하면 기본 0원
        }

        java.sql.Timestamp outTime = java.sql.Timestamp.valueOf(java.time.LocalDateTime.now());
        java.sql.Timestamp inTime = java.sql.Timestamp.valueOf(logDTO.getInTime());

        long durationMillis = outTime.getTime() - inTime.getTime();
        long durationMinutes = durationMillis / (1000 * 60);
        if (durationMinutes < 30) durationMinutes = 30;

        // 🚩 요금 정책(기본, 추가, 일일최대) DAO에서 불러옴
        int baseFee = PricingPolicyDAO.INSTANCE.getBaseFee();
        int extraFee = PricingPolicyDAO.INSTANCE.getExtraFee();
        int dailyMax = PricingPolicyDAO.INSTANCE.selectDailyMaxFee();

        double fee = 0.0;

        long totalHours = durationMinutes / 60;

        if (totalHours <= 24) {
            // 1일 이내라면 base + extra 적용, 최대요금 이상이면 최대요금
            double units = Math.ceil(durationMinutes / 30.0);
            fee = baseFee + (units - 2) * extraFee; // 첫 1시간은 baseFee, 이후부터 추가요금
            if (fee > dailyMax) {
                fee = dailyMax;
            }
        } else {
            // 24시간 초과 시: 24시간 단위 최대요금 + 나머지 시간 다시 과금
            long days = totalHours / 24;
            long remainingMinutes = durationMinutes - (days * 24 * 60);

            double units = Math.ceil(remainingMinutes / 30.0);
            double extraFeePart = baseFee + (units - 2) * extraFee;
            if (extraFeePart > dailyMax) extraFeePart = dailyMax;

            fee = (days * dailyMax) + extraFeePart;
        }

        return (int) fee;
    }

    /*
     * ✅ [할인 금액 계산]
     * - 원래 요금과 할인율을 받아서 할인 금액만 계산
     */
    public int calculateDiscountAmount(int originalFee, int discountRate) {
        return (originalFee * discountRate) / 100;
    }
}
