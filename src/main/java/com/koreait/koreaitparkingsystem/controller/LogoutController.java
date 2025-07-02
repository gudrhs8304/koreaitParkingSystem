package com.koreait.koreaitparkingsystem.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet("/logout.do")
public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp) throws jakarta.servlet.ServletException, java.io.IOException {
        log.info("***************LogoutController(doGet)*************");
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath() + "/login.do");
    }
}
