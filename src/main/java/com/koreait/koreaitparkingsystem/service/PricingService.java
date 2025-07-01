package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.dao.PricingPolicyDAO;
import com.koreait.koreaitparkingsystem.dto.PricingPolicyDTO;
import com.koreait.koreaitparkingsystem.util.MapperUtil;
import com.koreait.koreaitparkingsystem.vo.PricingPolicyVO;
import org.modelmapper.ModelMapper;

public enum PricingService {
    INSTANCE;

    private final PricingPolicyDAO dao = PricingPolicyDAO.INSTANCE;
    private final ModelMapper modelMapper = MapperUtil.INSTANCE.getInstance();

    public void updatePricingPolicy(PricingPolicyDTO pricingPolicyDTO) {
        PricingPolicyVO vo = modelMapper.map(pricingPolicyDTO, PricingPolicyVO.class);
        dao.updatePolicy(vo);
    }
    }

