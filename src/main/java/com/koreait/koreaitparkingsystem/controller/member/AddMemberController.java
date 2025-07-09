package com.koreait.koreaitparkingsystem.controller.member;

import com.koreait.koreaitparkingsystem.dto.MonthlyMemberDTO;
import com.koreait.koreaitparkingsystem.service.MonthlyMemberService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;

@Log4j2
@WebServlet("/addMember.do")
public class AddMemberController extends HttpServlet {

    private final MonthlyMemberService monthlyMemberService = MonthlyMemberService.INSTANCE;

    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp) throws jakarta.servlet.ServletException, java.io.IOException {
        log.info("***************AddMemberController(doGet)*************");
        req.getRequestDispatcher("/WEB-INF/views/member/addMember.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp) throws jakarta.servlet.ServletException, java.io.IOException {
        log.info("***************AddMemberController(doPost)*************");

        String carNumber = req.getParameter("carNumber");
        String driverName = req.getParameter("driverName");
        String phone = req.getParameter("phone");
        LocalDate startDate =  req.getParameter("startDate") != null ? LocalDate.parse(req.getParameter("startDate")) : null;
        LocalDate endDate = req.getParameter("endDate") != null ? LocalDate.parse(req.getParameter("endDate")) : null;

        log.info("carNumber: {}, driverName: {}, phone: {}, startDate: {}, endDate: {}", carNumber, driverName, phone, startDate, endDate);

        MonthlyMemberDTO dto = MonthlyMemberDTO.builder()
                .carNumber(carNumber)
                .driverName(driverName)
                .phone(phone)
                .startDate(startDate)
                .endDate(endDate)
                .build();
        log.info("dto: {}", dto);
        monthlyMemberService.addMonthlyMember(dto);
        resp.sendRedirect("/members.do");
    }
}
