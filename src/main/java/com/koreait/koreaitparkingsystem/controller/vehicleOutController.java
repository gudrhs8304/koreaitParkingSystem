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


        ParkingLogVO logVO = parkingLogDAO.selectLastLogByCarNumber(carNumber);
        if (logVO != null) {
            req.setAttribute("inTime", logVO.getInTime());
            req.setAttribute("fee", logVO.getFee());
        } else {
            req.setAttribute("error", "차량 로그를 찾을 수 없습니다.");
        }
        req.getRequestDispatcher("/WEB-INF/views/out/vehicleOut.jsp").forward(req,resp);
    }
}
