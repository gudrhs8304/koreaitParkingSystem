package com.koreait.koreaitparkingsystem.controller;

import com.koreait.koreaitparkingsystem.dao.ParkingLogDAO;
import com.koreait.koreaitparkingsystem.vo.ParkingLogVO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
@WebServlet("/disCount.do")
public class DisCountController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String carNumber = req.getParameter("carNumber");
        req.setAttribute("carNumber", carNumber);

        ParkingLogDAO parkingLogDAO = new ParkingLogDAO();
        ParkingLogVO logVO = parkingLogDAO.selectActiveLogByCarNumber(carNumber);

        String carTypeCode = null;
        int discountRate = 0;
        int discountAmount = 0;
        int finalFee = 0;

        if (logVO != null) {
            carTypeCode = logVO.getCarTypeCode();
            int fee = logVO.getFee();

            // fee가 0이면 실시간으로 계산
            if (fee == 0) {
                java.sql.Timestamp now = java.sql.Timestamp.valueOf(java.time.LocalDateTime.now());
                java.sql.Timestamp inTime = java.sql.Timestamp.valueOf(logVO.getInTime());

                long durationMillis = now.getTime() - inTime.getTime();
                long durationMinutes = durationMillis / (1000 * 60);
                if (durationMinutes < 30) durationMinutes = 30;

                double units = Math.ceil(durationMinutes / 30.0);
                fee = (int) (units * 3000.0);
            }

            // carTypeCode에 따른 할인율
            switch (carTypeCode) {
                case "disabled":
                    discountRate = 50;
                    break;
                case "compact":
                    discountRate = 30;
                    break;
                case "electric":
                    discountRate = 20;
                    break;
            }

            discountAmount = fee * discountRate / 100;
            finalFee = fee - discountAmount;
        }

        req.setAttribute("carTypeCode", carTypeCode);
        req.setAttribute("discountAmount", discountAmount);
        req.setAttribute("finalFee", finalFee);

        req.getRequestDispatcher("/WEB-INF/views/out/disCount.jsp").forward(req, resp);
    }
}
