package com.koreait.koreaitparkingsystem.controller;

import com.koreait.koreaitparkingsystem.dao.ParkingSpotDAO;
import com.koreait.koreaitparkingsystem.dao.ParkingLogDAO;

import com.koreait.koreaitparkingsystem.dto.CarDTO;
import com.koreait.koreaitparkingsystem.service.CarService;
import com.koreait.koreaitparkingsystem.service.MonthlyMemberService;
import com.koreait.koreaitparkingsystem.vo.ParkingLogVO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

/*
 * ✅ 출차 페이지 Controller
 * - 차량번호로 주차 로그 조회
 * - 입차 기록 없으면 에러
 * - 이미 출차된 차량이면 에러
 * - 정상 차량이면 요금 계산 & 결과 표시
 */
@Log4j2
@WebServlet("/vehicleOut.do")
public class vehicleOutController extends HttpServlet {

    // DAO 싱글톤 인스턴스 불러오기
    private final ParkingSpotDAO parkingSpotDAO = ParkingSpotDAO.INSTANCE;
    private final ParkingLogDAO parkingLogDAO = ParkingLogDAO.INSTANCE;

    /*
     * ✅ 출차 페이지 초기 진입(GET)
     * - 기본 화면만 보여줌
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/out/vehicleOut.jsp").forward(req,resp);
    }

    /*
     * ✅ 차량번호 검색 후 출차 정보 확인(POST)
     * - 차량번호로 주차 로그 검색
     * - 입차 기록 없으면: 에러 메시지 전달
     * - 이미 출차된 차량이면: 에러 메시지 전달
     * - 정상인 차량이면: 입차시간, 차량종류, 계산된 요금 전달
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 📌 클라이언트가 입력한 차량번호 가져오기
        String carNumber = req.getParameter("carNumber");
        log.info("vehicleOutController carNumber = {}", carNumber);

        // 📌 차량의 활성화된 주차 로그 가져오기
        ParkingLogVO logVO = parkingLogDAO.selectActiveLogByCarNumber(carNumber);
        log.info("vehicleOutController logVO = {}", logVO);

        if (logVO == null) {
            // 🚫 입차 기록 없음
            req.setAttribute("errorMessage", "입차 기록이 없습니다.");
        } else if (logVO.getOutTime() != null) {
            // 🚫 이미 출차된 차량
            req.setAttribute("errorMessage", "이미 출차된 차량입니다.");
        } else {// ✅ 월정액 회원 여부 체크
            boolean isMonthly = MonthlyMemberService.INSTANCE.isValidMonthlyMember(carNumber);

            if (isMonthly) {
                req.setAttribute("inTime", "월정액 회원");
                req.setAttribute("fee", "0");
            } else {
                // 📌 주차 요금 계산 로직
                java.sql.Timestamp now = java.sql.Timestamp.valueOf(java.time.LocalDateTime.now());
                java.sql.Timestamp inTime = java.sql.Timestamp.valueOf(logVO.getInTime());
                long durationMillis = now.getTime() - inTime.getTime();
                long durationMinutes = durationMillis / (1000 * 60);
                if (durationMinutes < 30) durationMinutes = 30;
                double units = Math.ceil(durationMinutes / 30.0);
                int fee = (int)(units * 3000.0);
                req.setAttribute("fee", fee);
                req.setAttribute("inTime", logVO.getInTime().toString().replace("T", " "));
            }
                req.setAttribute("carNumber", carNumber);
                req.setAttribute("carTypeCode", logVO.getCarTypeCode());
            // ✅ 정상 입차 상태인 차량



        }

        // 📌 결과 JSP 페이지로 포워드
        req.getRequestDispatcher("/WEB-INF/views/out/vehicleOut.jsp").forward(req, resp);
    }
}
