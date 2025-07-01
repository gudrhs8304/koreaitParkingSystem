package com.koreait.koreaitparkingsystem.filter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
@WebFilter(value = {"/entry.do", "/main.do", "/exit.do"})
public class LoginCheckFilter extends HttpFilter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        log.info("=== LoginCheckFilter ===");


        // 각 매개변수 형변환
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // 세션 로드
        HttpSession session = request.getSession();

        // 정적 메서드 필터 제외 처리
        String uri = request.getRequestURI();
        if (uri.startsWith("/assets/") || uri.endsWith(".css") || uri.endsWith(".js")) {
            chain.doFilter(req, res);
            return;
        }

        // 로그인 필터링
        if (session.getAttribute("admin") != null) {
            log.info("로그인된 사용자 접근 허용: {}", uri);
            chain.doFilter(req, res);
        } else {
            log.warn("비로그인 사용자 접근 차단: {} → /login.do", uri);
            response.sendRedirect("/login.do");
        }
    }
}
