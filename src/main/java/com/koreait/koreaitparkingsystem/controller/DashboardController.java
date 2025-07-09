package com.koreait.koreaitparkingsystem.controller;

import com.koreait.koreaitparkingsystem.dto.CarStatusDTO;
import com.koreait.koreaitparkingsystem.dto.ParkingSpotDTO;
import com.koreait.koreaitparkingsystem.service.CarStatusService;
import com.koreait.koreaitparkingsystem.service.ParkingSpotService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Log4j2
@WebServlet("/main.do")
public class DashboardController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("admin") == null) {
            log.warn("비로그인 사용자의 대시보드 접근 시도");
            resp.sendRedirect(req.getContextPath() + "/login.do");
            return;
        }

        try {
            // 대시보드 구현.
            List<ParkingSpotDTO> spots = ParkingSpotService.INSTANCE.getParkingSpots();
            int parkingSpots = spots.size();
            int usedCount = (int) spots.stream().filter(ParkingSpotDTO::isOccupied).count();
            int availableCount = spots.size() - usedCount;
            int overstayedCount = (int) CarStatusService.INSTANCE.countOverstayedCars();


            req.setAttribute("parkingSpots", parkingSpots);
            req.setAttribute("spots", spots);
            req.setAttribute("usedCount", usedCount);
            req.setAttribute("availableCount", availableCount);
            req.setAttribute("overstayedCount", overstayedCount); // 추후 구현
            req.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error("대시보드 처리 중 오류", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "대시보드 오류");
        }
    }

}
