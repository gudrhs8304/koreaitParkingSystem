package com.koreait.koreaitparkingsystem.controller;

import com.koreait.koreaitparkingsystem.dto.CarDTO;
import com.koreait.koreaitparkingsystem.dto.ParkingLogDTO;
import com.koreait.koreaitparkingsystem.service.EntryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.time.LocalDateTime;

@Log4j2
@WebServlet("/entry.do")
public class EntryController extends HttpServlet {

    private final EntryService entryService = EntryService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/in/vehicleIn.jsp").forward(req, resp);
        log.info("*************entryController(doGet)*************");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("*************entryController(doPost)*************");

        String carNumber = req.getParameter("carNumber");
        
        // 컨트롤러단에서 예외 처리할지 고민해봐야함.
//        if (carNumber == null || carNumber.isBlank()) {
//            req.setAttribute("error", "차량 번호는 필수입니다.");
//            req.getRequestDispatcher("/WEB-INF/views/in/vehicleIn.jsp").forward(req, resp);
//            return;
//        }
//
        String carTypeCode = req.getParameter("carTypeCode");
        String driverName = req.getParameter("driverName");
        String phone = req.getParameter("phone");

        log.info("번호: {} , 코드 : {} , 이름 : {} , 폰 : {}",carNumber,carTypeCode,driverName,phone);


        CarDTO carDTO = CarDTO.builder()
                .carNumber(carNumber)
                .carTypeCode(carTypeCode)
                .driverName(driverName)
                .phone(phone)
                .build();

        req.setAttribute("carDTO", carDTO);

        // 예외 발생 시 처리.
        try {
            entryService.processEntry(carDTO);
            resp.sendRedirect("/main.do");
        } catch (RuntimeException e) {
            log.error("입차 처리 중 오류 발생", e);
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/in/vehicleIn.jsp").forward(req, resp);
        }
    }
}
