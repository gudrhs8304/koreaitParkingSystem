package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.vo.AdminVO;
import com.koreait.koreaitparkingsystem.dao.AdminDAO;
import com.koreait.koreaitparkingsystem.dto.AdminDTO;
import com.koreait.koreaitparkingsystem.util.MapperUtil;
import org.modelmapper.ModelMapper;

public enum AdminService {
    instance;

    private final AdminDAO adminDAO;
    private final ModelMapper modelMapper;

    AdminService() {
        adminDAO = new AdminDAO();
        modelMapper = MapperUtil.INSTANCE.getInstance();
    }

    public boolean checkAdmin(AdminDTO adminDTO) {
        AdminDTO dto = modelMapper.map(adminDTO, AdminDTO.class);

        return adminDAO.login(dto.getUsername(), dto.getPassword());
    }

    public void updateAdmin(AdminDTO dto) {
        AdminVO vo = modelMapper.map(dto, AdminVO.class);
        adminDAO.updatePassword(vo.getUsername(), vo.getPassword());
    }
}
