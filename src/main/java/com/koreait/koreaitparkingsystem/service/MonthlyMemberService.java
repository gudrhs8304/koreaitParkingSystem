package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.dao.MonthlyMemberDAO;
import com.koreait.koreaitparkingsystem.dto.MonthlyMemberDTO;
import com.koreait.koreaitparkingsystem.util.MapperUtil;
import com.koreait.koreaitparkingsystem.vo.MonthlyMemberVO;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 월정액/연정액 회원 서비스
 * - 회원정보 조회, 등록, 수정, 삭제
 * - 기간에 따라 "월정액", "연정액" 뱃지 부여
 */

public enum MonthlyMemberService {
    INSTANCE;

    private final MonthlyMemberDAO monthlyMemberDAO = MonthlyMemberDAO.INSTANCE;
    private final ModelMapper modelMapper = MapperUtil.INSTANCE.getInstance();

    /**
     * 모든 회원 정보를 뱃지정보(연정액/월정액)와 함께 반환
     * - 1년 이상: "연정액회원" 뱃지
     * - 1개월~1년 미만: "월정액회원" 뱃지
     * - 이외: 뱃지 없음
     */

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

    public List<MonthlyMemberDTO> getMonthlyMembersWithBadge() {
        List<MonthlyMemberVO> voList = monthlyMemberDAO.selectMonthlyMembers();

        return voList.stream().map(vo -> {
            MonthlyMemberDTO dto = modelMapper.map(vo, MonthlyMemberDTO.class);

            long days = vo.getEndDate().toEpochDay() - vo.getStartDate().toEpochDay();

            if (days >= 365) {
                dto.setBadge("연정액회원"); // 1년 이상
            } else if (days >= 30) {
                dto.setBadge("월정액회원"); // 1달 이상 1년 미만
            } else {
                dto.setBadge(""); // 뱃지 없음
            }

            return dto;
        }).collect(Collectors.toList());
    }
}
