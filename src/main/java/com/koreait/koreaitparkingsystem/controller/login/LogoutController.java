package com.koreait.koreaitparkingsystem.controller.login;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet("/logout.do")
public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp) throws jakarta.servlet.ServletException, java.io.IOException {
        log.info("***************LogoutController(doGet)*************");
        
        // logout 쿠키 제거
        Cookie cookie = new Cookie("rememberAdmin", "");
        cookie.setMaxAge(0); // 쿠키 만료
        cookie.setPath(req.getContextPath());
        resp.addCookie(cookie);

        // 세션제거
        req.getSession().invalidate();

        // 로그인으로 리다이렉트
        resp.sendRedirect(req.getContextPath() + "/login.do");
    }
}
