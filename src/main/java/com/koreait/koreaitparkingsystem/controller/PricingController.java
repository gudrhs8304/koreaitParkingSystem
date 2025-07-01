package com.koreait.koreaitparkingsystem.controller;


import com.koreait.koreaitparkingsystem.dao.PricingPolicyDAO;
import com.koreait.koreaitparkingsystem.dto.PricingPolicyDTO;
import com.koreait.koreaitparkingsystem.service.DiscountPolicyService;
import com.koreait.koreaitparkingsystem.service.PricingService;
import com.koreait.koreaitparkingsystem.vo.DiscountPolicyVO;
import com.koreait.koreaitparkingsystem.vo.PricingPolicyVO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.List;

@Log4j2
@WebServlet("/pricing.do")
public class PricingController extends HttpServlet {
    private final PricingService pricingService = PricingService.INSTANCE;
    private final DiscountPolicyService discountPolicyService = DiscountPolicyService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // db 에서 전체 요금 목록 가져오기
        List<PricingPolicyVO> feeList = pricingService.getSelectAllFees();

        int baseFee = 0;
        int extraFee = 0;
        int maxFee = 0;

        for(PricingPolicyVO dto : feeList) {
            if (dto.getId() == 1) baseFee = dto.getPrice();
            else if (dto.getId() == 2) extraFee = dto.getPrice();
            else if(dto.getId() == 3) maxFee = dto.getPrice();
        }

        // 할인율 정보
        List<DiscountPolicyVO> discountList = discountPolicyService.getAllDiscounts();
        req.setAttribute("discountList", discountList);

        // 선택한 카테고리(기본값은 disabled)
        String selectedCategory = req.getParameter("discountCategory");
        if(selectedCategory == null) selectedCategory = "disabled";

        int selectedRate = 0;
        for(DiscountPolicyVO dto : discountList) {
            if(dto.getCarTypeCode().equals(selectedCategory)) {
                selectedRate = dto.getDiscountRate();
                break;
            }
        }

        req.setAttribute("baseFee", baseFee);
        req.setAttribute("extraFee", extraFee);
        req.setAttribute("maxFee", maxFee);
        req.setAttribute("discountCategory", selectedCategory);
        req.setAttribute("discountValue", selectedRate);
        req.setAttribute("discountList", discountList);

        // pricing.jsp로 forward
        req.setAttribute("pricings",pricingService.getSelectAllFees());
        req.getRequestDispatcher("/WEB-INF/views/pricing/pricing.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String carTypeCode = req.getParameter("discountCategory");
        int discountRate = Integer.parseInt(req.getParameter("discountValue"));

        discountPolicyService.editDiscountRate(carTypeCode, discountRate);

        int baseFee = Integer.parseInt(req.getParameter("baseFee"));
        int extraFee = Integer.parseInt(req.getParameter("extraFee"));
        int maxFee = Integer.parseInt(req.getParameter("maxFee"));
//        int selectedRate = Integer.parseInt(req.getParameter("discountRate"));

        pricingService.updateFees(1,baseFee);
        pricingService.updateFees(2,extraFee);
        pricingService.updateFees(3,maxFee);

        resp.sendRedirect("/main.do?message=변경사항이 수정되었습니다.");

//        resp.sendRedirect("/pricing.do?discountCategory=" + carTypeCode); // 선택값 유지해서 리다이렉트
    }
}
//        String code = req.getParameter("code");
//        int rate = Integer.parseInt(req.getParameter("rate"));
//
//
//        log.info("수정이 완료되었습니다. {}, {}", id, price);
//
//
//        // DTO를 가져와서 새로운 객체 생성 -> 담기
//
//        req.getRequestDispatcher("/pricing.do").forward(req, resp);
//        resp.sendRedirect("/main.do");
//    }
//}