package com.koreait.koreaitparkingsystem.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Log4j2
@WebFilter("/*")
public class LoginCheckFilter extends HttpFilter {

    // 필터에서 제외할 URI 목록 (로그인, 정적 자원 등)
    private static final List<String> EXCLUDE_URIS = Arrays.asList(
            "/login.do", "/logout.do", "/index.jsp",
            "/assets/", "/css/", "/js/", "/images/"
    );

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        log.info("==========LoginCheckFilter(doFilter)==========");

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String uri = request.getRequestURI();
        HttpSession session = request.getSession(false);

        // 정적 리소스 또는 예외 URI는 통과
        if (isExcluded(uri)) {
            chain.doFilter(req, res);
            return;
        }

        // 로그인 체크
        boolean loggedIn = session != null && session.getAttribute("admin") != null;

        if (loggedIn) {
            log.debug("로그인된 사용자 접근 허용: {}", uri);
            chain.doFilter(req, res);
        } else {
            log.warn(" 비로그인 사용자 접근 차단: {} → /login.do", uri);
            response.sendRedirect("/login.do");
        }
    }

    private boolean isExcluded(String uri) {
        return EXCLUDE_URIS.stream().anyMatch(uri::startsWith) ||
                uri.matches(".*(\\.css|\\.js|\\.png|\\.jpg|\\.woff2?|\\.svg)$");
    }
}