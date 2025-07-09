package com.koreait.koreaitparkingsystem.controller;

import com.koreait.koreaitparkingsystem.dto.CarDTO;
import com.koreait.koreaitparkingsystem.dto.ParkingLogDTO;
import com.koreait.koreaitparkingsystem.service.EntryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * ✅ EntryController
 * - 차량 입차 등록/조회 담당 컨트롤러.
 * - GET: 입차 화면 출력
 * - POST: 차량정보 받아 입차 등록 처리 (중복/실패/성공 메시지까지)
 */

@Log4j2
@WebServlet("/entry.do")
public class EntryController extends HttpServlet {



    private final EntryService entryService = EntryService.INSTANCE;

    /**
     * ✅ 입차 등록 화면 요청(GET)
     * - 단순히 입차 JSP 화면만 보여줌
     */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/in/vehicleIn.jsp").forward(req, resp);
        log.info("*************entryController(doGet)*************");
    }

    /**
     * ✅ 입차 등록 처리(POST)
     * - 차량번호/차종/운전자명/연락처 입력 받아 DTO 생성
     * - 서비스 호출해 입차 처리 시도 (중복입차, 예외처리 포함)
     * - 성공/실패 메시지 JSP로 전달
     */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("*************entryController(doPost)*************");

        // 📌 파라미터 추출
        String carNumber = req.getParameter("carNumber");
        
        // 컨트롤러단에서 예외 처리할지 고민해봐야함.
//        if (carNumber == null || carNumber.isBlank()) {
//            req.setAttribute("error", "차량 번호는 필수입니다.");
//            req.getRequestDispatcher("/WEB-INF/views/in/vehicleIn.jsp").forward(req, resp);
//            return;
//        }
//
        String carTypeCode = req.getParameter("carTypeCode");
        String driverName = req.getParameter("driverName");
        String phone = req.getParameter("phone");

        log.info("번호: {} , 코드 : {} , 이름 : {} , 폰 : {}",carNumber,carTypeCode,driverName,phone);

        // 📌 DTO 생성
        CarDTO carDTO = CarDTO.builder()
                .carNumber(carNumber)
                .carTypeCode(carTypeCode)
                .driverName(driverName)
                .phone(phone)
                .build();

        req.setAttribute("carDTO", carDTO);

        // 📌 입차 처리 + 예외/중복 체크
        try {
            entryService.processEntry(carDTO);
            req.setAttribute("successMessage", "입차 등록이 성공적으로 완료되었습니다.");
        } catch (RuntimeException e) {
            log.error("입차 처리 중 오류 발생", e);
            req.setAttribute("errorMessage", "입차 등록 실패: " + e.getMessage());
        }
        // 📌 결과를 JSP로 전달
        req.getRequestDispatcher("/WEB-INF/views/in/vehicleIn.jsp").forward(req, resp);
    }
}
