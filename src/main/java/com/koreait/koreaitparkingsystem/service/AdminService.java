package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.dao.AdminDAO;

public enum AdminService {
    instance;

    AdminDAO adminDAO;

    AdminService() {
        if(adminDAO == null) adminDAO = new AdminDAO();
    }

    public boolean checkAdmin(String email, String password) {
        return adminDAO.login(email, password);
    }

    public void updateAdmin(String email, String password) {
        adminDAO.updatePassword(email,password);
    }
}
