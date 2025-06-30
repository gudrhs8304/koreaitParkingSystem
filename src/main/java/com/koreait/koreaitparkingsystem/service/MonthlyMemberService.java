package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.dao.MonthlyMemberDAO;
import com.koreait.koreaitparkingsystem.dto.MonthlyMemberDTO;
import com.koreait.koreaitparkingsystem.util.MapperUtil;
import com.koreait.koreaitparkingsystem.vo.MonthlyMemberVO;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public enum MonthlyMemberService {
    INSTANCE;

    private final MonthlyMemberDAO monthlyMemberDAO = MonthlyMemberDAO.INSTANCE;
    private final ModelMapper modelMapper = MapperUtil.INSTANCE.getInstance();

    public List<MonthlyMemberDTO> getMonthlyMembers() {
        List<MonthlyMemberVO> voList = monthlyMemberDAO.selectMonthlyMembers();
        return voList.stream()
                .map(vo -> modelMapper.map(vo, MonthlyMemberDTO.class))
                .collect(Collectors.toList()); // 인텔리제이 설정으로 stream 으로 변경하였음.
    }

    public MonthlyMemberDTO getMonthlyMember(String carNumber) {
        MonthlyMemberVO vo = monthlyMemberDAO.selectByCarNumber(carNumber);
        return vo != null ? modelMapper.map(vo, MonthlyMemberDTO.class) : null;
    }

    public boolean isValidMonthlyMember(String carNumber) {
        return monthlyMemberDAO.isValidMember(carNumber);
    }

    public void addMonthlyMember(MonthlyMemberDTO dto) {
        MonthlyMemberVO vo = modelMapper.map(dto, MonthlyMemberVO.class);
        monthlyMemberDAO.insertMember(vo);
    }

    public void editMonthlyMember(MonthlyMemberDTO dto) {
        MonthlyMemberVO vo = modelMapper.map(dto, MonthlyMemberVO.class);
        monthlyMemberDAO.updateMember(vo);
    }

    public void removeMonthlyMember(String carNumber) {
        monthlyMemberDAO.deleteMemberByCarNumber(carNumber);
    }
}
