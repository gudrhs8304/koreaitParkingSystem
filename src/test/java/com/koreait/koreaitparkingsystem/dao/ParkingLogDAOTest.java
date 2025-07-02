//package com.koreait.koreaitparkingsystem.dao;
//
//import com.koreait.koreaitparkingsystem.vo.CarTypeVO;
//import com.koreait.koreaitparkingsystem.vo.ParkingLogVO;
//import lombok.extern.log4j.Log4j2;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@Log4j2
//class ParkingLogDAOTest {
//    private final ParkingLogDAO parkingLogDAO = ParkingLogDAO.INSTANCE;
//    private final CarDAO carDAO = CarDAO.INSTANCE;
//    private final CarTypeDAO carTypeDAO = CarTypeDAO.INSTANCE;
//
//    @Test
//    void selectActiveLogs() {
//        List<ParkingLogVO> activeLogs = parkingLogDAO.selectAllActiveLogs();
//
//        for (ParkingLogVO activeLog : activeLogs) {
//            log.info(activeLog);
//            log.info(carDAO.selectCarByNum(activeLog.getCarNumber()));
//            log.info(carTypeDAO.selectNameByCode(activeLog.getCarTypeCode()));
//            log.info("----------------");
//        }
//    }
//
//}