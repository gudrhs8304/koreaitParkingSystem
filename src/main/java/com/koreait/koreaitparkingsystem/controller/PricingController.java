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
import java.net.URLEncoder;
import java.util.List;

@Log4j2
@WebServlet("/pricing.do")
public class PricingController extends HttpServlet {
    private final PricingService pricingService = PricingService.INSTANCE;
    private final DiscountPolicyService discountPolicyService = DiscountPolicyService.INSTANCE;
    private final PricingPolicyDAO pricingPolicyDAO = PricingPolicyDAO.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // db 에서 전체 요금 목록 가져오기
        List<PricingPolicyVO> pricingList = PricingService.INSTANCE.getSelectAllFees();
        List<DiscountPolicyVO> discountList = DiscountPolicyService.INSTANCE.getAllDiscounts();

        req.setAttribute("pricingList", pricingList);
        req.setAttribute("discountList", discountList);

//        // 선택한 카테고리(기본값은 disabled)
//        String selectedCategory = req.getParameter("discountCategory");
//        if(selectedCategory == null) selectedCategory = "disabled";
//
//        int selectedRate = 0;
//        for(DiscountPolicyVO dto : discountList) {
//            if(dto.getCarTypeCode().equals(selectedCategory)) {
//                selectedRate = dto.getDiscountRate();
//                break;

//                req.setAttribute("discountCategory", );
        String message = req.getParameter("message");
        if (message != null) {
            req.setAttribute("message", message);
        }
        log.info("*******doGet2***********");

        // pricing.jsp로 forward
//        req.setAttribute("pricings",pricingService.getSelectAllFees());
        // 올바른 코드
        req.getRequestDispatcher("/WEB-INF/views/pricing/pricing.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("*******doPost 진입함**********");

        req.removeAttribute("message");
        // 요금 설정 업데이트
        String baseFeeParam = req.getParameter("baseFee");
        String extraFeeParam = req.getParameter("extraFee");
        String maxFeeParam = req.getParameter("maxFee");
        log.info("베이스피" + baseFeeParam);

        if (baseFeeParam != null && !baseFeeParam.isEmpty()) {
            pricingService.updateFees(1, Integer.parseInt(baseFeeParam));
            log.info(baseFeeParam);

            if (extraFeeParam != null && !extraFeeParam.isEmpty()) {
                pricingService.updateFees(2, Integer.parseInt(extraFeeParam));
                log.info(extraFeeParam);

                if (maxFeeParam != null && !maxFeeParam.isEmpty()) {
                    pricingService.updateFees(3, Integer.parseInt(maxFeeParam));
                    log.info(maxFeeParam);

                    log.info("baseFee 업데이트 " + baseFeeParam);
//        pricingService.updateFees(1, baseFeeParam);

                    // 할인율 설정 업데이트
                    String disabledParam = req.getParameter("disabled");
                    String compactParam = req.getParameter("compact");
                    String electricParam = req.getParameter("electric");
                    String normalParam = req.getParameter("normal");

                    if (disabledParam != null && !disabledParam.isEmpty()) {
                        discountPolicyService.editDiscountRate("disabled", Integer.parseInt(disabledParam));
                    }
                    if (compactParam != null && !compactParam.isEmpty()) {
                        discountPolicyService.editDiscountRate("compact", Integer.parseInt(compactParam));
                    }
                    if (electricParam != null && !electricParam.isEmpty()) {
                        discountPolicyService.editDiscountRate("electric", Integer.parseInt(electricParam));
                    }
                    if (normalParam != null && !normalParam.isEmpty()) {
                        discountPolicyService.editDiscountRate("normal", Integer.parseInt(normalParam));
                    }


                    req.setAttribute("message", "변경이 완료되었습니다.");
                    doGet(req, resp);
//                    resp.sendRedirect("/pricing.do");
                }
            }
        }
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