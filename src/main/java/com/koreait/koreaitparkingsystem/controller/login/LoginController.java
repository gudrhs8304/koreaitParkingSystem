package com.koreait.koreaitparkingsystem.controller.login;


import com.koreait.koreaitparkingsystem.dto.AdminDTO;
import com.koreait.koreaitparkingsystem.service.AdminService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
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

        Cookie[] cookies = req.getCookies();
        String rememberedUser = null;

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("rememberAdmin")) {
                    rememberedUser = c.getValue();
                    break;
                }
            }
        }

        if (session != null && session.getAttribute("admin") != null) {
            // 이미 로그인됨
            resp.sendRedirect(req.getContextPath() + "/main.do");
        } else if (rememberedUser != null) {
            // 자동 로그인 처리
            AdminDTO dto = AdminDTO.builder()
                    .username(rememberedUser)
                    .build();

            session = req.getSession();
            session.setAttribute("admin", dto);
            log.info("쿠키 통해 자동 로그인 성공: {}", rememberedUser);
            resp.sendRedirect(req.getContextPath() + "/main.do");
        }

        req.getRequestDispatcher("/WEB-INF/views/login/login.jsp").forward(req, resp);

    }

   @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("*************loginController*************");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        log.info("로그인 연결 {} , {}", username, password);

        AdminDTO dto = AdminDTO.builder()
                .username(username)
                .password(password)
                .build();

        boolean success = adminService.checkAdmin(dto);

        if (success) {
            dto.setPassword(null); // dto 의 패스워드 삭제
            HttpSession session = req.getSession(); // 세션 선언
            session.setAttribute("admin", dto); // 세션 저장

            if (req.getParameter("remember") != null) { // 자동로그인용 파라미터 확인. ( 체크박스의 remember 확인 )
                Cookie cookie = new Cookie("rememberAdmin", dto.getUsername());
                cookie.setMaxAge(60 * 60 * 24 * 7); // 7일
                cookie.setPath(req.getContextPath());
                resp.addCookie(cookie);
            }
            resp.sendRedirect(req.getContextPath() + "/main.do"); // 메인으로 바로연결.
        } else {
            log.warn("아이디 또는 비밀번호가 올바르지 않습니다.");
            req.setAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/WEB-INF/views/login/login.jsp").forward(req, resp);
        }
    }
}
