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
            resp.sendRedirect("/main.do");
        } else { // false 면 다시 로그인 화면으로. 차후 js 에서 alter 출력 예정.
            log.warn("아이디 또는 비밀번호가 올바르지 않습니다.");
            req.setAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            req.getRequestDispatcher("/WEB-INF/views/login/login.jsp").forward(req, resp);
        }


    }
}
