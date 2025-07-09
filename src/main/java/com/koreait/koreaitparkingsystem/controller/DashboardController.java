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

    /**
     * ✅ GET 요청 처리
     * - 세션에서 로그인 여부 확인.
     * - 자리 정보 가져와서 사용 중/사용 가능 자리 계산.
     * - JSP에 전달하여 대시보드 출력.
     */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        // ✅ 비로그인 사용자는 로그인 페이지로 리다이렉트
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
        // ✅ 자리 정보 가져오기
        List<ParkingSpotDTO> spots = ParkingSpotService.INSTANCE.getParkingSpotsWithCarNumber();
        int usedCount = (int) spots.stream().filter(ParkingSpotDTO::isOccupied).count();
        int availableCount = spots.size() - usedCount;


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
        // ✅ JSP에 데이터 전달
        req.setAttribute("spots", spots);
        req.setAttribute("usedCount", usedCount);
        req.setAttribute("availableCount", availableCount);
        req.setAttribute("overstayedCount", 0); // 추후 구현


        // ✅ 대시보드 화면으로 이동
        req.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req, resp);
    }

}
