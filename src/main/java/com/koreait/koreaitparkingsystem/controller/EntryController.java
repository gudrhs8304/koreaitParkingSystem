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

        entryService.processEntry(carDTO);

        resp.sendRedirect("/main.do");
    }
}
