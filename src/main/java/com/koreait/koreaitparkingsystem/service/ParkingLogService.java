package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.dao.*;
import com.koreait.koreaitparkingsystem.dto.CarDTO;
import com.koreait.koreaitparkingsystem.dto.ParkingLogDTO;
import com.koreait.koreaitparkingsystem.util.MapperUtil;
import com.koreait.koreaitparkingsystem.vo.ParkingLogVO;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.sql.Timestamp;

/**
 * ✅ ParkingLogService
 * - 주차 로그(입출차 기록) 관련 서비스 로직 담당
 * - 출차/입차/요금계산 등 도메인 핵심 로직을 제공
 */

@Log4j2
public enum ParkingLogService {
    INSTANCE;

    // ... DAO, 서비스, 맵퍼 등 싱글톤 선언

    /**
     * ✅ 출차 처리
     * - 활성 주차 로그가 존재하면 출차 시간과 요금을 계산해서 DB에 저장
     * - 월정액 회원일 경우 요금은 0원 처리
     * @param carDTO 출차할 차량 정보
     */

    private final ParkingSpotDAO parkingSpotDAO =  ParkingSpotDAO.INSTANCE;
    final ParkingLogDAO parkingLogDAO  =  ParkingLogDAO.INSTANCE;
    private final CarService carService  =  CarService.INSTANCE;
    final ModelMapper modelMapper = MapperUtil.INSTANCE.getInstance();
    private final CarTypeDAO carTypeDAO = CarTypeDAO.INSTANCE;
    private final DiscountPolicyDAO discountPolicyDAO = DiscountPolicyDAO.INSTANCE;
    private final MonthlyMemberService monthlyMemberService =  MonthlyMemberService.INSTANCE;
    private final CarDAO carDAO = CarDAO.INSTANCE;

    // ✅ 출차 처리: ParkingLog 테이블에 출차 시간/요금 갱신 (fee 직접 전달받음)
    public void updateParkingLog(CarDTO carDTO) {
        ParkingLogVO activeLogVO = parkingLogDAO.selectActiveLogByCarNumber(carDTO.getCarNumber());

        if (activeLogVO != null) {
            ParkingLogDTO dto = modelMapper.map(activeLogVO, ParkingLogDTO.class);

            // ✅ 항상 최신 요금 계산 (cap 포함!)
            int finalFee = TotalFeeService.INSTANCE.calculateFee(dto);

            // ✅ 월정액 여부 확인
            boolean isMonthly = monthlyMemberService.isValidMonthlyMember(carDTO.getCarNumber());
            int feeToSave = isMonthly ? 0 : finalFee;

            // ✅ 출차 시간 세팅 및 DB 반영
            Timestamp outTime = Timestamp.valueOf(java.time.LocalDateTime.now());
            parkingLogDAO.updateExit(carDTO.getCarNumber(), outTime, feeToSave);

            log.info("ParkingLogService: 출차 저장됨! 시간: " + outTime + ", 요금: " + feeToSave);
        } else {
            log.info("ParkingLogService: 활성 주차 기록이 없습니다.");
        }
    }

    // ✅ 현재 입차 상태인 ParkingLog 가져오기
    public ParkingLogDTO getActiveLogByCarNumber(String carNumber) {
        ParkingLogVO vo = parkingLogDAO.selectActiveLogByCarNumber(carNumber);
        return vo != null ? modelMapper.map(vo, ParkingLogDTO.class) : null;
    }

    // ✅ 마지막 ParkingLog 가져오기 (출차 후 기록 포함)
    public ParkingLogDTO getLastLogByCarNumber(String carNumber) {
        ParkingLogVO vo = parkingLogDAO.selectLastLogByCarNumber(carNumber);
        return vo != null ? modelMapper.map(vo, ParkingLogDTO.class) : null;
    }

    // ✅ 주차요금 계산: ParkingLogDTO로 inTime ~ 현재시간 기준
    public int calculateFee(ParkingLogDTO logDTO) {
        return TotalFeeService.INSTANCE.calculateFee(logDTO);
    }
}
