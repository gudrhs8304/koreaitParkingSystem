package com.koreait.koreaitparkingsystem.filter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
@WebFilter(value = {"/entry.do",""})
public class LoginCheckFilter extends HttpFilter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        log.info("===loginCheckFilter===");

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();

        if (session.getAttribute("admin") != null) {
            log.info("로그인된 사용자 접근 허용");
            chain.doFilter(req, res);
            return;
        }

        log.warn("비로그인 사용자 접근 차단 -> /login 리다이렉트");
        response.sendRedirect("/login.do");
        return; //
    }
}
