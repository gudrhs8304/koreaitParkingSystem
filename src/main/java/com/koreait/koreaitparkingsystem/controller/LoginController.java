package com.koreait.koreaitparkingsystem.controller;


import com.koreait.koreaitparkingsystem.service.AdminService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
@WebServlet("/login.do")
public class LoginController extends HttpServlet {

    private final AdminService adminService = AdminService.instance;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("userName");
        String password = req.getParameter("password");

        log.info("로그인 연결 {} , {}", username, password);

        boolean success = adminService.checkAdmin(username, password);

        if (success) {
            HttpSession session = req.getSession();
            session.setAttribute("admin", username);
            resp.sendRedirect("/main.do");
        } else {
            log.info("아이디 또는 비밀번호가 올바르지 않습니다.");
            req.setAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            req.getRequestDispatcher("/WEB-INF/views/login/login.jsp").forward(req, resp);
        }


    }
}
