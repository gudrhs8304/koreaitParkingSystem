package com.koreait.koreaitparkingsystem.controller;


import com.koreait.koreaitparkingsystem.dto.AdminDTO;
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

        HttpSession session = req.getSession(false); // 이미 세션이 존재하면 만들지 말라는 의미 false.

        // 이미 세션이 존재하거나 admin이 not null 일경우 -> 이미 로그인 했으므로 메인으로 리다이렉트함.
        if (session != null && session.getAttribute("admin") != null) {
            log.info("이미 로그인된 사용자, 메인으로 리다이렉트");
            resp.sendRedirect(req.getContextPath() + "/main.do");
            return;
        }
        req.getRequestDispatcher("/WEB-INF/views/login/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("*************loginController*************");

        // 파라미터로 id / pw 가져옴
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        log.info("로그인 연결 {} , {}", username, password);

        // dto 객체 생성
        AdminDTO dto = AdminDTO.builder()
                .username(username)
                .password(password)
                .build();

        boolean success = adminService.checkAdmin(dto); // 서비스에서 check

        if (success) {  // true면 main 으로
            dto.setPassword(null);
            HttpSession session = req.getSession();
            session.setAttribute("admin", dto);
            resp.sendRedirect(req.getContextPath() + "/main.do");
        } else { // false 면 다시 로그인 화면으로. 차후 js 에서 alter 출력 예정.
            log.warn("아이디 또는 비밀번호가 올바르지 않습니다.");
            req.setAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/WEB-INF/views/login/login.jsp").forward(req, resp);
        }


    }
}
