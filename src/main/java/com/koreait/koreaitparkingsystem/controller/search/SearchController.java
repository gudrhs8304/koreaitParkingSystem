package com.koreait.koreaitparkingsystem.controller.search;

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
        try {
            String keyword = req.getParameter("keyword"); // 검색
            String pageParam = req.getParameter("page"); // 페이지

            // 🔹 유효성 검사: page 파라미터 정수 검증
            int page;
            try {
                page = (pageParam == null || pageParam.isBlank()) ? 1 : Integer.parseInt(pageParam);
                if (page < 1) page = 1;
            } catch (NumberFormatException e) {
                page = 1;
            }

            // 🔹 유효성 검사: keyword 길이 체크 (선택 사항)
            if (keyword != null && keyword.trim().length() < 2) {
                req.setAttribute("error", "검색어는 2글자 이상 입력해주세요.");
                req.getRequestDispatcher("/WEB-INF/views/search/search.jsp").forward(req, resp);
                return;
            }

            int pageSize = 5;

            // 🔹 검색 결과 조회
            List<CarStatusDTO> allList = carStatusService.getStatusList(keyword);
            int totalCount = allList.size();

            int totalPage = (totalCount / pageSize) + (totalCount % pageSize > 0 ? 1 : 0);
            if (page > totalPage) page = totalPage;

            int start = (page - 1) * pageSize;
            int end = Math.min(page * pageSize, totalCount);

            List<CarStatusDTO> pageList = (start < end) ? allList.subList(start, end) : List.of();


            // 🔹 주차장 현황 정보
            int total = carStatusService.getTotalSpots();          // 전체 공간 수
            int used = carStatusService.getUsedSpots();            // 출차 안 된 차량 수
            int empty = total - used;                                   // 빈자리 계산


            // 🔹 결과 전달
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

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "검색 중 시스템 오류가 발생했습니다.");
            req.getRequestDispatcher("/WEB-INF/views/search/search.jsp").forward(req, resp);
        }
    }
}
