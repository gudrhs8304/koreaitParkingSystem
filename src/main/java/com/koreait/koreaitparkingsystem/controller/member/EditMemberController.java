package com.koreait.koreaitparkingsystem.controller.member;

import com.koreait.koreaitparkingsystem.dto.MonthlyMemberDTO;
import com.koreait.koreaitparkingsystem.service.MonthlyMemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.time.LocalDate;

@Log4j2
@WebServlet("/editMember.do")
public class EditMemberController extends HttpServlet {

    private final MonthlyMemberService monthlyMemberService = MonthlyMemberService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("***************EditMemberController(doGet)*************");
        String carNumber = req.getParameter("carNumber");

        MonthlyMemberDTO member = monthlyMemberService.getMonthlyMember(carNumber);
        req.setAttribute("member", member);
        req.getRequestDispatcher("/WEB-INF/views/member/editMember.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("***************EditMemberController(doPost)*************");

        String carNumber = req.getParameter("carNumber");
        String driverName = req.getParameter("driverName");
        String phone = req.getParameter("phone");
        LocalDate startDate = req.getParameter("startDate") != null ? LocalDate.parse(req.getParameter("startDate")) : null;
        LocalDate endDate = req.getParameter("endDate") != null ? LocalDate.parse(req.getParameter("endDate")) : null;

        MonthlyMemberDTO memberDTO = MonthlyMemberDTO.builder()
                .carNumber(carNumber)
                .driverName(driverName)
                .phone(phone)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        monthlyMemberService.editMonthlyMember(memberDTO);

        resp.sendRedirect("/members.do");
    }
}
