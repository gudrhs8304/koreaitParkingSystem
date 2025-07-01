package com.koreait.koreaitparkingsystem.controller;

import com.koreait.koreaitparkingsystem.dao.ParkingSpotDAO;
import com.koreait.koreaitparkingsystem.dao.ParkingLogDAO;

import com.koreait.koreaitparkingsystem.vo.ParkingLogVO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
@WebServlet("/vehicleOut.do")
public class vehicleOutController extends HttpServlet {
    private final ParkingSpotDAO parkingSpotDAO = ParkingSpotDAO.INSTANCE;
    private final ParkingLogDAO parkingLogDAO = ParkingLogDAO.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/out/vehicleOut.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String carNumber = req.getParameter("carNumber");
        req.setAttribute("carNumber", carNumber);

        ParkingLogVO logVO = parkingLogDAO.selectActiveLogByCarNumber(carNumber);
        if (logVO != null && logVO.getOutTime() == null) {
            req.setAttribute("inTime", logVO.getInTime().toString().replace("T", " "));

            java.sql.Timestamp now = java.sql.Timestamp.valueOf(java.time.LocalDateTime.now());
            java.sql.Timestamp inTime = java.sql.Timestamp.valueOf(logVO.getInTime());
            long durationMillis = now.getTime() - inTime.getTime();
            long durationMinutes = durationMillis / (1000 * 60);
            if (durationMinutes < 30) durationMinutes = 30;
            double units = Math.ceil(durationMinutes / 30.0);
            int fee = (int)(units * 3000.0);

            req.setAttribute("fee", fee);
        } else {
            req.setAttribute("error", "출차된 차량이거나 주차 기록이 없습니다.");
        }

        req.getRequestDispatcher("/WEB-INF/views/out/vehicleOut.jsp").forward(req, resp);
    }
}
