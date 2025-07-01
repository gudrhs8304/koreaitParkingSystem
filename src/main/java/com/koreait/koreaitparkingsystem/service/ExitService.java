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
import jakarta.servlet.http.HttpServletRequest;
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


    public void searchCarAndSetAttribute(HttpServletRequest req, CarDTO carDTO) {
        ParkingLogDTO dto = ParkingLogService.INSTANCE.getActiveLogByCarNumber(carDTO.getCarNumber());

        if (dto == null) {
            req.setAttribute("errorMessage", "차량 정보가 없습니다.");
            return;
        }
        if (dto.getInTime() == null) {
            req.setAttribute("errorMessage", "입차 기록이 없습니다.");
            return;
        }
        if (dto.getOutTime() != null) {
            req.setAttribute("errorMessage", "이미 출차된 차량입니다.");
            return;
        }

        int finalFee = TotalFeeService.INSTANCE.calculateFee(dto);

        req.setAttribute("inTime", dto.getInTime().toString().replace("T", " "));
        req.setAttribute("fee", finalFee);
        req.setAttribute("carNumber", carDTO.getCarNumber());
    }

    public void setCarTypeCodeForDiscount(HttpServletRequest req, CarDTO carDTO) {
        // 차량 정보 조회
        CarDTO result = CarService.INSTANCE.getCar(carDTO);

        if (result != null) {
            String carTypeCode = result.getCarTypeCode();
            // 라디오 버튼 선택을 위해 carTypeCode 를 JSP에 전달
            req.setAttribute("carTypeCode", carTypeCode);
        } else {
            req.setAttribute("errorMessage", "해당 차량 정보가 존재하지 않습니다.");
        }
    }

    public void calculateDiscountedFee(HttpServletRequest req, CarDTO carDTO, String discountType) {
        // 1) 최근 출차 로그 가져오기 (DTO)
        ParkingLogDTO logDTO = ParkingLogService.INSTANCE.getLastLogByCarNumber(carDTO.getCarNumber());
        if (logDTO == null) {
            req.setAttribute("errorMessage", "출차된 차량의 로그를 찾을 수 없습니다.");
            return;
        }

        int originalFee = logDTO.getFee();

        // 2) DiscountPolicyService 통해 할인율 가져오기
        int discountRate = 0;
        if (discountType != null && !discountType.isEmpty()) {
            discountRate = DiscountPolicyService.INSTANCE.getDiscountRateByTypeCode(discountType);
        }

        // 3) TotalFeeService로 할인 금액 계산 로직 재사용 가능
        int discountAmount = TotalFeeService.INSTANCE.calculateDiscountAmount(originalFee, discountRate);
        int finalFee = originalFee - discountAmount;

        // 4) 결과 세팅
        req.setAttribute("originalFee", originalFee);
        req.setAttribute("discountAmount", discountAmount);
        req.setAttribute("finalFee", finalFee);
        req.setAttribute("discountRate", discountRate);
    }

    public void prepareDiscountPage(HttpServletRequest req, String carNumber) {
        ParkingLogDTO logDTO = ParkingLogService.INSTANCE.getActiveLogByCarNumber(carNumber);

        if (logDTO == null) {
            req.setAttribute("error", "주차 기록을 찾을 수 없습니다.");
            return;
        }

        String carTypeCode = logDTO.getCarTypeCode();

        // 요금 가져오기 (출차 전이면 실시간 계산)
        int fee = logDTO.getFee();
        if (fee == 0) {
            fee = ParkingLogService.INSTANCE.calculateFee(logDTO);
        }

        // 할인율 가져오기
        int discountRate = 0;
        switch (carTypeCode) {
            case "disabled":
                discountRate = 50; break;
            case "compact":
                discountRate = 30; break;
            case "electric":
                discountRate = 20; break;
        }

        int discountAmount = fee * discountRate / 100;
        int finalFee = fee - discountAmount;

        req.setAttribute("carNumber", carNumber);
        req.setAttribute("carTypeCode", carTypeCode);
        req.setAttribute("discountAmount", discountAmount);
        req.setAttribute("finalFee", finalFee);
    }

    public void calculateDiscountAndSetAttributes(HttpServletRequest req, String carNumber, String discountType) {
        log.info("✅ calculateDiscountAndSetAttributes 시작");
        log.info("carNumber = " + carNumber);
        log.info("discountType = " + discountType);

        ParkingLogDTO logDTO = ParkingLogService.INSTANCE.getActiveLogByCarNumber(carNumber);
        if (logDTO == null) {
            log.info("❌ logDTO is null");
            req.setAttribute("error", "차량 정보를 찾을 수 없습니다.");
            return;
        }

        int originalFee = ParkingLogService.INSTANCE.calculateFee(logDTO);
        log.info("originalFee = " + originalFee);

        int discountRate = DiscountPolicyDAO.INSTANCE.selectDiscountRate(discountType);
        log.info("discountRate = " + discountRate);

        int discountAmount = originalFee * discountRate / 100;
        int finalFee = originalFee - discountAmount;

        log.info("discountAmount = " + discountAmount);
        log.info("finalFee = " + finalFee);

        req.setAttribute("discountAmount", discountAmount);
        req.setAttribute("finalFee", finalFee);
    }

    public void processExit(CarDTO carDTO) {
        log.info("✅ processExit 시작");
        log.info("차량번호 = " + carDTO.getCarNumber());

        // 1. 활성 로그 가져오기
        ParkingLogDTO logDTO = ParkingLogService.INSTANCE.getActiveLogByCarNumber(carDTO.getCarNumber());
        if (logDTO == null) {
            log.warn("❌ 활성 주차 기록이 없습니다. 출차 처리 불가.");
            throw new RuntimeException("출차 처리할 입차 기록이 없습니다.");
        }

        // 2. 요금 계산
        int fee = ParkingLogService.INSTANCE.calculateFee(logDTO);
        log.info("💰 계산된 주차 요금 = " + fee);

        // 3. parking_log 출차시간, 요금 update
        ParkingLogService.INSTANCE.updateParkingLog(carDTO);

        // 4. parking_spot 자리 비우기
        ParkingSpotService.INSTANCE.isParkingSpot(carDTO);

        log.info("✅ 출차 처리 완료");
    }

    public void processExitByCarNumber(String carNumber) {
        CarDTO carDTO = CarDTO.builder().carNumber(carNumber).build();

        // 1. 출차 로그 업데이트
        ParkingLogService.INSTANCE.updateParkingLog(carDTO);

        // 2. 주차 자리 상태 비움 처리
        ParkingSpotService.INSTANCE.isParkingSpot(carDTO);

        // 필요하다면 추가 처리 가능!
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
