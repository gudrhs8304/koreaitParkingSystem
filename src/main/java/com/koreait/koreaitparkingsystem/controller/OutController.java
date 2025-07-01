package com.koreait.koreaitparkingsystem.controller;

import com.koreait.koreaitparkingsystem.dto.CarDTO;
import com.koreait.koreaitparkingsystem.service.ExitService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
@WebServlet("/out.do")
public class OutController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String carNumber = req.getParameter("carNumber");
        log.info("✅ OutController.doPost 시작, 차량번호: {}", carNumber);

        if (carNumber == null || carNumber.isBlank()) {
            req.setAttribute("errorMessage", "차량 번호가 없습니다.");
            req.getRequestDispatcher("/WEB-INF/views/out/vehicleOut.jsp").forward(req, resp);
            return;
        }

        try {
            ExitService.INSTANCE.processExitByCarNumber(carNumber);
            req.setAttribute("successMessage", "출차 처리 완료!");
        } catch (RuntimeException e) {
            log.error("❌ 출차 처리 실패: {}", e.getMessage());
            req.setAttribute("errorMessage", "출차 처리 중 오류 발생: " + e.getMessage());
        }

        req.getRequestDispatcher("/WEB-INF/views/out/out.jsp").forward(req, resp);
    }
}