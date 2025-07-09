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
import java.util.List;

/**
 * ✅ MembersController
 * - 월정액/연정액 회원 리스트 페이지 컨트롤러
 * - 회원 정보 + 뱃지정보 포함해서 JSP로 전달
 */


@Log4j2
@WebServlet("/members.do")
public class MembersController extends HttpServlet {

    /**
     * ✅ 회원 리스트 화면(GET)
     * - 서비스에서 회원/기간정보/뱃지정보 가져와 JSP로 전달
     */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("*******************MEMBER CONTROLLER(doGet)******************");

        // 👉 월정액/연정액 회원 리스트+뱃지정보 가져오기
        List<MonthlyMemberDTO> members = MonthlyMemberService.INSTANCE.getMonthlyMembersWithBadge();

        // 👉 JSP에 데이터 전달
        req.setAttribute("members", members);

        // 👉 회원 리스트 JSP로 이동
        req.getRequestDispatcher("/WEB-INF/views/member/member.jsp").forward(req, resp);
    }
}
