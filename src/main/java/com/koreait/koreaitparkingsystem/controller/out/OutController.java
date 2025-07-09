package com.koreait.koreaitparkingsystem.controller.out;

import com.koreait.koreaitparkingsystem.service.ExitService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
@WebServlet("/out.do")
public class OutController extends HttpServlet {

    /*
     * ✅ [출차 처리 POST]
     * - 차량번호(carNumber) 파라미터를 받아서 출차 처리 시도
     * - 유효하지 않으면 에러 메시지 출력 후 vehicleOut.jsp로 돌아감
     * - 정상 처리되면 ExitService 통해 DB에 출차시간 기록 및 주차자리 상태 업데이트
     * - 성공 시 out.jsp로 이동하여 처리 완료 메시지 출력
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 클라이언트에서 전달한 차량번호 파라미터 읽기
        String carNumber = req.getParameter("carNumber");
        log.info("OutController 21 ✅ OutController.doPost 시작, 차량번호: {}", carNumber);

        // 차량번호가 없으면 vehicleOut.jsp로 에러메시지와 함께 돌아감
        if (carNumber == null || carNumber.isBlank()) {
            req.setAttribute("errorMessage", "차량 번호가 없습니다.");
            req.getRequestDispatcher("/WEB-INF/views/out/vehicleOut.jsp").forward(req, resp);
            return;
        }

        try {
            // 출차 처리 로직 실행: ParkingLog 출차시간 기록 & 주차공간 비우기
            ExitService.INSTANCE.processExitByCarNumber(carNumber);
            req.setAttribute("successMessage", "출차 처리 완료!");
        } catch (RuntimeException e) {
            log.error("OutController 33 ❌ 출차 처리 실패: {}", e.getMessage());
            req.setAttribute("errorMessage", "출차 처리 중 오류 발생: " + e.getMessage());
        }

        // 결과 페이지로 이동
        req.getRequestDispatcher("/WEB-INF/views/out/out.jsp").forward(req, resp);
    }
}