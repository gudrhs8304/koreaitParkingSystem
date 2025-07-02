package com.koreait.koreaitparkingsystem.controller;

import com.koreait.koreaitparkingsystem.dao.ParkingLogDAO;
import com.koreait.koreaitparkingsystem.dto.CarDTO;
import com.koreait.koreaitparkingsystem.dto.ParkingLogDTO;
import com.koreait.koreaitparkingsystem.service.CarService;
import com.koreait.koreaitparkingsystem.service.ExitService;
import com.koreait.koreaitparkingsystem.service.ParkingLogService;
import com.koreait.koreaitparkingsystem.vo.ParkingLogVO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.net.URLEncoder;

@Log4j2
@WebServlet("/disCount.do")
public class DisCountController extends HttpServlet {
    ParkingLogDAO parkingLogDAO = ParkingLogDAO.INSTANCE;
    ParkingLogService parkingLogService = ParkingLogService.INSTANCE;

    /*
     * ✅ [할인 적용 페이지 진입 처리]
     * - 차량번호(carNumber) 파라미터로 주차 로그를 조회
     * - 차량 타입(carTypeCode)을 가져와서 라디오버튼 자동 선택용으로 JSP에 전달
     * - 할인율 및 할인금액 계산에 필요한 데이터 준비
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String carNumber = req.getParameter("carNumber");
        log.info("DiscountController 26 넘어온 차량번호 = {}", carNumber);

        // 주차 로그 조회 → carTypeCode 셋팅
        ParkingLogDTO parkingLogDTO = parkingLogService.getActiveLogByCarNumber(carNumber);
        if(parkingLogDTO != null){
            String carTypeCode = parkingLogDTO.getCarTypeCode();
            req.setAttribute("carTypeCode", carTypeCode);
            log.info("DisCountController 35 carTypeCode = {}", carTypeCode);
        } else {
            req.setAttribute("DisCountController 37 errorMessage", "주차 기록을 찾을 수 없습니다.");
        }

        // 할인 계산에 필요한 데이터 준비
        if (carNumber != null && !carNumber.isBlank()) {
            ExitService.INSTANCE.prepareDiscountPage(req, carNumber);
        }
        log.info("DisCountController 43 prepareDiscountPage = {}", carNumber);

        req.setAttribute("carNumber", carNumber);

        // 선택된 차량타입코드 기반으로 할인타입 세팅
        ExitService.INSTANCE.setCarTypeCodeForDiscount(req, CarDTO.builder().carNumber(carNumber).build());
        req.setAttribute("discountType", req.getParameter("discountType"));
        log.info("DiscountController 49 discountType = {}", req.getParameter("discountType"));

        req.getRequestDispatcher("/WEB-INF/views/out/disCount.jsp").forward(req, resp);
    }

    /*
     * ✅ [할인 적용/출차 처리 POST]
     * - 차량번호, 할인타입 등 파라미터 수신
     * - 주차로그 유효성 체크: 입차 기록이 없거나 이미 출차한 차량이면 vehicleOut.jsp로 리턴
     * - 할인적용 로직 호출: 할인금액/최종요금 계산
     * - 출차처리 버튼 클릭 시 실제 출차 처리(출차시간 기록, 스팟 비우기)
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String carNumber = req.getParameter("carNumber");
        String action = req.getParameter("submitExit");

        String discountType = req.getParameter("discountType");

        String carTypeCode = req.getParameter("carTypeCode");

        // 차량 종류가 normal 일 경우 할인적용 불가
        if("normal".equalsIgnoreCase(carTypeCode)){
            req.setAttribute("errorMessage", "할인 대상 차량이 아닙니다.");
            req.getRequestDispatcher("/WEB-INF/views/out/vehicleOut.jsp").forward(req, resp);
            return;
        }

        ParkingLogVO parkingLogVO = parkingLogDAO.selectActiveLogByCarNumber(carNumber);

        CarDTO carDTO = CarDTO.builder().carNumber(carNumber).build();

        // 차량타입코드 재확인해서 할인적용
        ExitService.INSTANCE.setCarTypeCodeForDiscount(req, carDTO);

        // discountType이 null이면 carTypeCode로 대체
        if(discountType == null || discountType.isBlank()){
            discountType = (String) req.getAttribute("carTypeCode");
            log.info("DisCountController 71 discountType = {}", discountType);
        }

        // 할인 적용금액, 최종금액 계산
        ExitService.INSTANCE.calculateDiscountAndSetAttributes(req, carNumber, discountType);

        // 차량 입차기록 없거나 이미 출차된 차량이면 경고 후 리턴
        if (parkingLogVO == null || parkingLogVO.getOutTime() != null) {
            req.setAttribute("errorMessage", "유효하지 않은 차량입니다.");
            req.getRequestDispatcher("/WEB-INF/views/out/vehicleOut.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("carTypeCode", parkingLogVO.getCarTypeCode());

        // 출차 처리 버튼 눌렀을 경우 → 출차 처리 후 out.do로 리다이렉트
        if ("출차 처리".equals(req.getParameter("submitExit"))) {
            log.info("DisCountController 85 출차처리 실행 시작 carNumber={}", carNumber);
            ExitService.INSTANCE.processExit(carDTO);
            resp.sendRedirect("/out.do");
            return;
        }

        // 할인 적용 버튼 처리 (할인 다시 계산)
        ExitService.INSTANCE.setCarTypeCodeForDiscount(req, CarDTO.builder().carNumber(carNumber).build());
        req.setAttribute("discountType", req.getParameter("discountType"));
        log.info("DiscountController 49 discountType = {}", req.getParameter("discountType"));

        // ⭐ 할인 계산 처리
        ExitService.INSTANCE.calculateDiscountAndSetAttributes(req, carNumber, discountType);

        req.setAttribute("carNumber", carNumber);
        req.setAttribute("discountType", discountType);

        req.getRequestDispatcher("/WEB-INF/views/out/disCount.jsp").forward(req, resp);
    }
}
