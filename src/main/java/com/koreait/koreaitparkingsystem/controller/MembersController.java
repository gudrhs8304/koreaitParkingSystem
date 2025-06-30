package com.koreait.koreaitparkingsystem.controller;

import com.koreait.koreaitparkingsystem.dto.MonthlyMemberDTO;
import com.koreait.koreaitparkingsystem.service.MonthlyMemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.List;

@Log4j2
@WebServlet("/members.do")
public class MembersController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("*******************MEMBER CONTROLLER(doGet)******************");
        List<MonthlyMemberDTO> members = MonthlyMemberService.INSTANCE.getMonthlyMembers();
        req.setAttribute("members", members);
        req.getRequestDispatcher("/WEB-INF/views/member/member.jsp").forward(req, resp);
    }
}
