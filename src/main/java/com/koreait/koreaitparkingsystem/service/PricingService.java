package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.dao.PricingPolicyDAO;
import com.koreait.koreaitparkingsystem.dto.PricingPolicyDTO;
import com.koreait.koreaitparkingsystem.util.MapperUtil;
import com.koreait.koreaitparkingsystem.vo.PricingPolicyVO;
import org.modelmapper.ModelMapper;

import java.util.List;

public enum PricingService {
    INSTANCE;

    private final PricingPolicyDAO dao = PricingPolicyDAO.INSTANCE;
    private final ModelMapper modelMapper = MapperUtil.INSTANCE.getInstance();

    public void updatePricingPolicy(PricingPolicyDTO pricingPolicyDTO) {
        PricingPolicyVO vo = modelMapper.map(pricingPolicyDTO, PricingPolicyVO.class);
        dao.updatePolicy(vo);
    }

    public void updateFees(int id,int price) {
        dao.updateFeeById(id,price);
    }
    public void updateFeeById(int id,int price) {
        dao.updateFeeById(id,price);
    }

    public PricingPolicyDTO searchPolicy(int id) {
        PricingPolicyVO vo = dao.selectPriceById(id);
        return modelMapper.map(vo, PricingPolicyDTO.class);
    }
    public List<PricingPolicyVO> getSelectAllFees() {
        return dao.selectAllFees(); // DAO에서 전체 요금 목록을 가져오는 메서드
    }
}

