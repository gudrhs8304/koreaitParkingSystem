package com.koreait.koreaitparkingsystem.controller;

import com.koreait.koreaitparkingsystem.dao.ParkingLogDAO;
import com.koreait.koreaitparkingsystem.dto.CarDTO;
import com.koreait.koreaitparkingsystem.service.CarService;
import com.koreait.koreaitparkingsystem.service.ExitService;
import com.koreait.koreaitparkingsystem.vo.ParkingLogVO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
@WebServlet("/disCount.do")
public class DisCountController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String carNumber = req.getParameter("carNumber");
        log.info("넘어온 차량번호 = {}", carNumber);

        if (carNumber != null && !carNumber.isBlank()) {
            ExitService.INSTANCE.prepareDiscountPage(req, carNumber);
        }

        req.setAttribute("carNumber", carNumber);

        req.getRequestDispatcher("/WEB-INF/views/out/disCount.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String carNumber = req.getParameter("carNumber");
        String discountType = req.getParameter("discountType");



        if (carNumber != null && !carNumber.isBlank()) {
            // ExitService 에서 할인 계산하고 결과 세팅
            ExitService.INSTANCE.calculateDiscountAndSetAttributes(req, carNumber, discountType);
        }

        req.setAttribute("carNumber", carNumber);
        req.setAttribute("discountType", discountType);

        log.info("=== DisCountController.doPost ===");
        log.info("carNumber = " + carNumber);
        log.info("discountType = " + discountType);

        req.getRequestDispatcher("/WEB-INF/views/out/disCount.jsp").forward(req, resp);
    }
}
