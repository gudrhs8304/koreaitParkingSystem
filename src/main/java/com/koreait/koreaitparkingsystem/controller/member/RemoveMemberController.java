package com.koreait.koreaitparkingsystem.controller.member;

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
        try {
            monthlyMemberService.removeMonthlyMember(carNumber);
        }catch (Exception e) {
            log.error(e);
            req.setAttribute("errorMsg","회원 정보 삭제 중 오류가 발생했습니다");
            req.getRequestDispatcher("/WEB-INF/views/member/editMember.jsp").forward(req, resp);
            return;
        }

        resp.sendRedirect("/members.do");
    }
}
