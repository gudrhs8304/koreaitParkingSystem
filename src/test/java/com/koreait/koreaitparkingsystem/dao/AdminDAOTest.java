package com.koreait.koreaitparkingsystem.dao;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
class AdminDAOTest {
   private final AdminDAO dao = new AdminDAO();

    @Test
    void login() {
        log.info(dao.login("admin", "1234"));
    }

    @Test
    void updatePassword() {
    }
}