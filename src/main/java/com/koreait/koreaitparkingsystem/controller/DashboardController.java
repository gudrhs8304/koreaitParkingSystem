package com.koreait.koreaitparkingsystem.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
@WebServlet("/main.do")
public class DashboardController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("admin") == null) {
            log.warn("비로그인 사용자의 대시보드 접근 시도");
            resp.sendRedirect(req.getContextPath() + "/login.do");
            return;
        }

        req.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req, resp);
    }

}
