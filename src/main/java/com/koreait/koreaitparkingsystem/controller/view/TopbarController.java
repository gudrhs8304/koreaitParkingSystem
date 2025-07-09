package com.koreait.koreaitparkingsystem.controller.view;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.net.URLEncoder;

@Log4j2
@WebServlet("/topbar.do")
public class TopbarController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("***************TopbarController(doPost)*************");
        String carNumber = req.getParameter("carNumber");
        log.info("carNumber: {}", carNumber);

        String encodedCarNumber = URLEncoder.encode(carNumber, "UTF-8");
        resp.sendRedirect("/search.do?carNumber=" + encodedCarNumber);

    }
}
