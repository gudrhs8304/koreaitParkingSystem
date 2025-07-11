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

    // 서비스 클래스는 비즈니스 로직을 담당하며, 싱글톤으로 선언하여 메모리 효율적으로 사용
    private final CarStatusService carStatusService = CarStatusService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 클라이언트로부터 전달된 검색어(keyword)와 페이지번호(page)를 가져옴
            String keyword = req.getParameter("keyword");
            String pageParam = req.getParameter("page");

            int page;
            try {
                // page 파라미터가 없거나 공백이면 기본값 1, 숫자가 아니면 예외 발생
                page = (pageParam == null || pageParam.isBlank()) ? 1 : Integer.parseInt(pageParam);
                if (page < 1) page = 1; // 음수 방지
            } catch (NumberFormatException e) {
                page = 1; // 숫자가 아닌 값이 들어온 경우 안전하게 1페이지 처리
            }

            // 총 주차 공간 수, 사용 중인 공간 수를 조회해서 빈 자리 수를 계산
            int total = carStatusService.getTotalSpots();
            int used = carStatusService.getUsedSpots();
            int empty = total - used;

            // JSP로 주차 현황(전체/사용/빈자리) 정보를 전달
            req.setAttribute("total", total);
            req.setAttribute("used", used);
            req.setAttribute("emptySpot", empty);

            // 검색어가 입력되었을 때, 최소 2글자 이상인지 검사 (너무 짧으면 의미 없는 검색 방지)
            if (keyword != null && !keyword.isBlank() && keyword.trim().length() < 2) {
                // 검색어가 너무 짧으면 에러 메시지와 빈 결과 리스트를 설정하고 JSP로 이동
                req.setAttribute("error", "검색어는 2글자 이상 입력해주세요.");
                req.setAttribute("carList", List.of());       // 빈 검색 결과
                req.setAttribute("page", 1);                  // 현재 페이지
                req.setAttribute("totalCount", 0);            // 검색 결과 개수 0
                req.setAttribute("totalPage", 0);             // 페이지 수 0
                req.setAttribute("pageSize", 5);              // 페이지당 항목 수
                req.setAttribute("keyword", keyword);         // 기존 검색어 유지
                req.getRequestDispatcher("/WEB-INF/views/search/search.jsp").forward(req, resp);
                return; // 더 이상 진행하지 않고 종료
            }

            // 키워드로 차량 정보를 모두 가져옴 (전체 리스트)
            List<CarStatusDTO> allList = carStatusService.getStatusList(keyword);

            // 전체 검색 결과 개수
            int totalCount = allList.size();

            // 페이지당 보여줄 항목 수
            int pageSize = 5;

            // 전체 페이지 수 계산 (예: 13개 → 3페이지)
            int totalPage = (totalCount / pageSize) + (totalCount % pageSize > 0 ? 1 : 0);

            // 현재 요청한 페이지 번호가 전체 페이지 수를 넘지 않도록 보정
            if (totalPage == 0) page = 1;
            else if (page > totalPage) page = totalPage;

            // 현재 페이지에 보여줄 데이터의 시작 인덱스와 끝 인덱스 계산
            int start = (page - 1) * pageSize;
            int end = Math.min(start + pageSize, totalCount); // 리스트 범위 초과 방지

            // 부분 리스트(현재 페이지에 해당하는 데이터만) 추출
            List<CarStatusDTO> pageList = (start < totalCount) ? allList.subList(start, end) : List.of();

            // JSP로 검색 결과 및 페이징 관련 정보 전달
            req.setAttribute("carList", pageList);        // 현재 페이지 차량 리스트
            req.setAttribute("page", page);               // 현재 페이지 번호
            req.setAttribute("totalCount", totalCount);   // 전체 검색 결과 수
            req.setAttribute("totalPage", totalPage);     // 전체 페이지 수
            req.setAttribute("pageSize", pageSize);       // 한 페이지에 표시할 항목 수
            req.setAttribute("keyword", keyword);         // 검색어 유지

            // JSP로 이동 (forward 방식)
            req.getRequestDispatcher("/WEB-INF/views/search/search.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace(); // 콘솔에 예외 로그 출력

            // 예외 발생 시 사용자에게 에러 메시지와 빈 리스트 전달
            req.setAttribute("error", "검색 중 시스템 오류가 발생했습니다.");
            req.setAttribute("carList", List.of());
            req.setAttribute("page", 1);
            req.setAttribute("totalCount", 0);
            req.setAttribute("totalPage", 0);
            req.setAttribute("pageSize", 5);
            req.getRequestDispatcher("/WEB-INF/views/search/search.jsp").forward(req, resp);
        }
    }
}
