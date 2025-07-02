package com.koreait.koreaitparkingsystem.controller;

import com.koreait.koreaitparkingsystem.service.MonthlyMemberService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet("/removeMember.do")
public class RemoveMemberController extends HttpServlet {

    MonthlyMemberService monthlyMemberService = MonthlyMemberService.INSTANCE;

    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp) throws jakarta.servlet.ServletException, java.io.IOException {
        log.info("***************RemoveMemberController(doGet)*************");
        String carNumber = req.getParameter("carNumber");
        log.info("carNumber: {}", carNumber);
        monthlyMemberService.removeMonthlyMember(carNumber);
        resp.sendRedirect("/members.do");
    }
}
