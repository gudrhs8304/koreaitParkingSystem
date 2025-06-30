package com.koreait.koreaitparkingsystem.controller;

import com.koreait.koreaitparkingsystem.dao.PricingPolicyDAO;
import com.koreait.koreaitparkingsystem.dto.PricingPolicyDTO;
import com.koreait.koreaitparkingsystem.service.PricingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
@WebServlet (urlPatterns = {"/pricing.do"})
public class PricingController extends HttpServlet {
//    private final PricingService pricingService = PricingService.instance();
    // 요금 관리 페이지 화면 열기 위해 사용
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PricingService pricingService = new PricingService();
        Object fee = null;
        req.setAttribute("fee", fee);

        req.getRequestDispatcher("/WEB-INF/views/pricing/pricing.jsp").forward(req, resp);
    }


    // 요금수정관리를 위해 doPost를 사용한다. -> 많은 데이터를 사용, 민감한 정보를 보낼때 사용.(주소록에 보이지 않음), 로그인,회원가입 비밀번호 등 사용됨.
    // 요금관리 페이지에서는 기본요금, 추가요금, 일일최대요금 수정 등 할 수 있다. -> 요금관리페이지에서 액션 처리용으로 사용
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 사용자가 보낸 값을 가져오기, 파라미터로 id 가져오기.
        int id = Integer.parseInt(req.getParameter("id"));
        int price = Integer.parseInt(req.getParameter("price"));

        log.info("수정이 완료되었습니다. {}, {}", id, price);


        // DTO를 가져와서 새로운 객체 생성 -> 담기
        PricingPolicyDTO pricingPolicyDTO = new PricingPolicyDTO();
        pricingPolicyDTO.setId(id);
        pricingPolicyDTO.setPrice(price);

        // 서비스 호출해서 새로운 객체 생성 -> 수정하기
        PricingService pricingService = new PricingService();

        resp.sendRedirect("/pricing.do");
    }
}