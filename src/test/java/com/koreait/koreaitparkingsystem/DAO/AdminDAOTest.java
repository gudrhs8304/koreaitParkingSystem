package com.koreait.koreaitparkingsystem.DAO;

import com.koreait.koreaitparkingsystem.VO.AdminVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

@Log4j2
class AdminDAOTest {
    private final AdminDAO adminDAO = AdminDAO.getInstance();

    @Test
    void selectLogin() {
        if(adminDAO.selectLogin("admin1","hashed_pw_1")) {
            log.info("Login Successful");
        } else {
            log.info("Login Failed");
        }
    }

    @Test
    void selectAdmin() {
        String username = "admin1";
        if(username.equals(adminDAO.selectAdmin(username))) {
            log.info(username);
            log.info("Login Successful");
        } else  {
            log.info("Login Failed");
        }
    }
}