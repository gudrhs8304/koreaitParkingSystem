package com.koreait.koreaitparkingsystem.controller;

import com.koreait.koreaitparkingsystem.dto.CarParkingLogDTO;
import com.koreait.koreaitparkingsystem.dto.CarStatusDTO;
import com.koreait.koreaitparkingsystem.service.CarStatusService;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/search.do")
public class SearchController extends HttpServlet {
    private final CarStatusService parkingStatusService = CarStatusService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");

        // 검색어 기반 목록만 추출 (목록만 필터링됨)
        List<CarStatusDTO> carList = parkingStatusService.getStatusList(keyword);

        // 전체 현황은 항상 고정된 전체 기준으로 유지
        int total = parkingStatusService.getTotalSpots();          // 전체 공간 수
        int used = parkingStatusService.getUsedSpots();            // 출차 안 된 차량 수
        int empty = total - used;                                   // 빈자리 계산

        req.setAttribute("carList", carList);
        req.setAttribute("total", total);
        req.setAttribute("used", used);
        req.setAttribute("emptySpot", empty);
        req.getRequestDispatcher("/WEB-INF/views/search/search.jsp").forward(req, resp);
    }
}

