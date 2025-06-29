package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.dao.CarDAO;
import com.koreait.koreaitparkingsystem.dao.MonthlyMemberDAO;
import com.koreait.koreaitparkingsystem.vo.CarVO;
import com.koreait.koreaitparkingsystem.vo.MonthlyMemberVO;

import java.util.List;

public enum CarService {
    INSTANCE;

    private final CarDAO carDAO;

    CarService() {
        carDAO = new CarDAO();
    }

    public CarVO getCar(String carNumber) {
        return carDAO.selectCarByNum(carNumber);
    }
    public void addCar(CarVO carVO) {
        carDAO.insertCar(carVO);
    }
    public boolean isRegistered(String carNumber) {
        return carDAO.selectIsRegistered(carNumber);
    }
}
