//package com.koreait.koreaitparkingsystem.service;
//
//import com.koreait.koreaitparkingsystem.dao.ParkingSpotDAO;
//
//public enum ParkingSpotService {
//    INSTANCE;
//
//    private final ParkingSpotDAO spotDAO = ParkingSpotDAO.INSTANCE;
//
//    public Integer assignSpot() {
//        return spotDAO.assignSpot();
//    }
//
//    public void occupySpot(int spotNumber) {
//        spotDAO.occupySpot(spotNumber);
//    }
//
//    public void releaseSpot(int spotNumber) {
//        spotDAO.releaseSpot(spotNumber);
//    }
//
//    public int getAvailableSpotCount() {
//        return spotDAO.getAvailableSpotCount();
//    }
//}
