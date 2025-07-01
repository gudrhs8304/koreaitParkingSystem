package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.dao.*;
import com.koreait.koreaitparkingsystem.dto.CarDTO;
import com.koreait.koreaitparkingsystem.dto.ParkingLogDTO;
import com.koreait.koreaitparkingsystem.util.MapperUtil;
import com.koreait.koreaitparkingsystem.vo.ParkingLogVO;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

@Log4j2
public enum ParkingLogService {
    INSTANCE;

    private final ParkingSpotDAO parkingSpotDAO =  ParkingSpotDAO.INSTANCE;
    final ParkingLogDAO parkingLogDAO  =  ParkingLogDAO.INSTANCE;
    private final CarService carService  =  CarService.INSTANCE;
    final ModelMapper modelMapper = MapperUtil.INSTANCE.getInstance();
    private final CarTypeDAO carTypeDAO = CarTypeDAO.INSTANCE;
    private final DiscountPolicyDAO discountPolicyDAO = DiscountPolicyDAO.INSTANCE;
    private final MonthlyMemberService monthlyMemberService =  MonthlyMemberService.INSTANCE;
    private final CarDAO carDAO = CarDAO.INSTANCE;


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

    public ParkingLogDTO getActiveLogByCarNumber(String carNumber) {
        ParkingLogVO vo = parkingLogDAO.selectActiveLogByCarNumber(carNumber);
        return vo != null ? modelMapper.map(vo, ParkingLogDTO.class) : null;
    }

    public ParkingLogDTO getLastLogByCarNumber(String carNumber) {
        ParkingLogVO vo = parkingLogDAO.selectLastLogByCarNumber(carNumber);
        return vo != null ? modelMapper.map(vo, ParkingLogDTO.class) : null;
    }

    public int calculateFee(ParkingLogDTO logDTO) {
        java.sql.Timestamp outTime = java.sql.Timestamp.valueOf(java.time.LocalDateTime.now());
        java.sql.Timestamp inTime = java.sql.Timestamp.valueOf(logDTO.getInTime());

        long durationMillis = outTime.getTime() - inTime.getTime();
        long durationMinutes = durationMillis / (1000 * 60);

        if (durationMinutes < 30) durationMinutes = 30;

        double baseRatePer30Min = 3000.0;
        double fee = (durationMinutes / 30.0) * baseRatePer30Min;

        return (int) fee;
    }
}
