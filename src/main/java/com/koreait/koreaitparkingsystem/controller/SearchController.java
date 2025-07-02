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
    private final CarStatusService carStatusService = CarStatusService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword"); // 검색
        String pageParam = req.getParameter("page"); // 페이지

        int page = (pageParam == null) ? 1 : Integer.parseInt(pageParam);
        int pageSize = 5;

        // 검색어 기반 목록만 추출 (목록만 필터링됨)
        List<CarStatusDTO> allList = carStatusService.getStatusList(keyword);
        int totalCount = allList.size();

        int totalPage = (totalCount / pageSize) + (totalCount % pageSize > 0 ? 1 : 0);
        if (page < 1) page = 1;
        if (page > totalPage) page = totalPage;

        int start = (page - 1) * pageSize;
        int end = Math.min(page * pageSize, totalCount);

        List<CarStatusDTO> pageList = (start < end) ? allList.subList(start, end) : List.of();


        // 전체 현황은 항상 고정된 전체 기준으로 유지
        int total = carStatusService.getTotalSpots();          // 전체 공간 수
        int used = carStatusService.getUsedSpots();            // 출차 안 된 차량 수
        int empty = total - used;                                   // 빈자리 계산

        req.setAttribute("carList", pageList);
        req.setAttribute("total", total);
        req.setAttribute("used", used);
        req.setAttribute("emptySpot", empty);
        req.setAttribute("page", page);
        req.setAttribute("totalCount", totalCount);
        req.setAttribute("totalPage", totalPage);
        req.setAttribute("pageSize", pageSize);
        req.setAttribute("keyword", keyword);
        req.getRequestDispatcher("/WEB-INF/views/search/search.jsp").forward(req, resp);
    }
}

