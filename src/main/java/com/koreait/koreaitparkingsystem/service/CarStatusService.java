package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.dao.CarStatusDAO;
import com.koreait.koreaitparkingsystem.dto.CarStatusDTO;
import com.koreait.koreaitparkingsystem.vo.CarParkingLogVO;
import com.koreait.koreaitparkingsystem.vo.CarStatusVO;
import lombok.extern.log4j.Log4j2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum CarStatusService {
    INSTANCE;
    private final CarStatusDAO carDAO = CarStatusDAO.INSTANCE;

    public List<CarStatusDTO> getStatusList(String keyword) {
        List<CarParkingLogVO> logs = (keyword == null || keyword.isBlank())
                ? carDAO.selectAllActiveLogs()
                : carDAO.selectLogs(keyword);

        return logs.stream().map(item -> {
                    CarStatusVO car = carDAO.findByCarNumber(item.getCarNumber());
                    String type = carDAO.selectNameByCode(item.getCarTypeCode());
//                    log.info(car);
                    int hours = (int) Duration.between(item.getInTime(), LocalDateTime.now()).toHours();
                    return CarStatusDTO.builder()
                            .carNumber(item.getCarNumber())
                            .carTypeName(type == null ? "-" : type)
                            .driverName(car == null ? "-" : car.getDriverName())
                            .phone(car == null ? "-" : car.getPhone())
                            .inTime(item.getInTime())
                            .parkedHours(hours)
                            .over12Hours(hours >= 12)
                            .over12HoursIcon(hours >= 12 ? " ⚠️" : "")
                            .build();
                })
                .sorted(Comparator.comparing(CarStatusDTO::getParkedHours).reversed())
                .collect(Collectors.toList());
    }


    public int getTotalSpots() {
        return carDAO.countAllSpots(); // 전체 주차공간 수
    }

    public int getUsedSpots() {
        return carDAO.countActiveLogs(); // 출차하지 않은 차량 수
    }

    public long countOverstayedCars() {
        List<CarStatusDTO> cars = getStatusList(null); // 전체 검색
        return cars.stream()
                .filter(CarStatusDTO::isOver12Hours)
                .count();
    }

}