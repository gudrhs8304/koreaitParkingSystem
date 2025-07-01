package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.dao.*;
import com.koreait.koreaitparkingsystem.dto.CarDTO;
import com.koreait.koreaitparkingsystem.dto.CarTypeDTO;
import com.koreait.koreaitparkingsystem.dto.MonthlyMemberDTO;
import com.koreait.koreaitparkingsystem.dto.ParkingLogDTO;
import com.koreait.koreaitparkingsystem.vo.CarTypeVO;
import com.koreait.koreaitparkingsystem.util.MapperUtil;
import com.koreait.koreaitparkingsystem.vo.CarVO;
import com.koreait.koreaitparkingsystem.vo.MonthlyMemberVO;
import com.koreait.koreaitparkingsystem.vo.ParkingLogVO;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
@Log4j2
public enum ExitService {
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
            log.info("차량 타입: " + result.getCarTypeCode());
        } else {
            log.info("해당 차량이 존재하지 않습니다.");
        }
    }

    public void printCarType(CarDTO carDTO) {
        CarDTO result = carService.getCar(carDTO);
        if (result != null) {
            String carTypeCode = result.getCarTypeCode();
            CarTypeDTO carTypeDTO = null;

            for (CarTypeVO ctVO : carTypeDAO.selectCarType()) {
                CarTypeDTO ct = modelMapper.map(ctVO, CarTypeDTO.class);
                if (ct.getCode().equals(carTypeCode)) {
                    carTypeDTO = ct;
                    break;
                }
            }

            if (carTypeDTO != null) {
                log.info("차량 이름: " + carTypeDTO.getName());
            } else {
                log.info("차종 정보를 찾을 수 없습니다.");
            }
        } else {
            log.info("해당 차량이 존재하지 않습니다.");
        }
    }

    public void printDiscountPolicy(CarDTO carDTO) {
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

    public void printMonthlyMember(CarDTO carDTO) {
        MonthlyMemberDTO dto = monthlyMemberService.getMonthlyMember(carDTO.getCarNumber()); // 병민 의견 -> 멤버서비스의 dto 변환으로 dto 타입으로 변경하였음.
        MonthlyMemberDTO member = null;
        if (dto != null) {
            member = modelMapper.map(dto, MonthlyMemberDTO.class);
        }
        if (member != null) {
            log.info("월정액 회원입니다. " + member);
        } else {
            log.info("해당 차량은 월정액 회원이 아닙니다.");
        }
    }

    public void updateParkingLog(CarDTO carDTO) {
        ParkingLogVO activeLogVO = parkingLogDAO.selectActiveLogByCarNumber(carDTO.getCarNumber());
        ParkingLogDTO activeLog = null;
        if (activeLogVO != null) {
            activeLog = modelMapper.map(activeLogVO, ParkingLogDTO.class);
        }

        if (activeLog != null) {
            java.sql.Timestamp outTime = java.sql.Timestamp.valueOf(java.time.LocalDateTime.now());
            java.sql.Timestamp inTime = java.sql.Timestamp.valueOf(activeLog.getInTime());

            long durationMillis = outTime.getTime() - inTime.getTime();
            long durationMinutes = durationMillis / (1000 * 60);
            if (durationMinutes < 30) durationMinutes = 30;

            double baseRatePer30Min = 3000.0;
            double fee = (durationMinutes / 30.0) * baseRatePer30Min;

            int finalFee = (int) fee;

            parkingLogDAO.updateExit(carDTO.getCarNumber(), outTime, finalFee);
            log.info("출차 시간이 정상적으로 저장되었습니다. 시간: " + outTime + ", 요금: " + finalFee);
        } else {
            log.info("출차 기록을 찾을 수 없습니다.");
        }
    }

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

//    public void searchCar(CarDTO carDTO) {
//        CarVO carVO = carDAO.selectCarByNum(carDTO.getCarNumber());
//        carDAO.selectCarByNum(carDTO.getCarNumber()) == parkingLogDAO.selectLastLogByCarNumber(carDTO.getCarNumber())
//        CarDTO car = null;
//        if (carVO.getOutTime() != null) {
//            car = modelMapper.map(carVO, CarDTO.class);
//            log.info(car.getCarNumber());
//        } else {
//            log.info("차량이 없습니다.");
//        }
//    }
}
