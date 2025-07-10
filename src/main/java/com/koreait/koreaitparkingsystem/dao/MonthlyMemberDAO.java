package com.koreait.koreaitparkingsystem.dao;

import com.koreait.koreaitparkingsystem.util.DBConnection;
import com.koreait.koreaitparkingsystem.vo.MonthlyMemberVO;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public enum MonthlyMemberDAO {
    INSTANCE;

    public List<MonthlyMemberVO> selectMonthlyMembers() {
        String sql = "select * from monthly_member";
        List<MonthlyMemberVO> monthlyMemberVOList = new ArrayList<>();
        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                MonthlyMemberVO monthlyMemberVO = MonthlyMemberVO.builder()
                        .carNumber(resultSet.getString("car_number"))
                        .driverName(resultSet.getString("driver_name"))
                        .phone(resultSet.getString("phone"))
                        .startDate(resultSet.getDate("start_date").toLocalDate())
                        .endDate(LocalDate.parse(resultSet.getString("end_date")))
                        .build();
                monthlyMemberVOList.add(monthlyMemberVO);
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return monthlyMemberVOList;
    }
    public MonthlyMemberVO selectByCarNumber(String carNumber) {
        String sql = "SELECT * FROM monthly_member WHERE car_number = ?";
        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, carNumber);
            @Cleanup ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return MonthlyMemberVO.builder()
                        .carNumber(rs.getString("car_number"))
                        .driverName(rs.getString("driver_name"))
                        .phone(rs.getString("phone"))
                        .startDate(rs.getDate("start_date").toLocalDate())
                        .endDate(rs.getDate("end_date").toLocalDate())
                        .build();
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return null;
    }
    public boolean isValidMember(String carNumber) {
        String sql = "SELECT 1 FROM monthly_member WHERE car_number = ? AND start_date <= CURDATE() AND end_date >= CURDATE()";
        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, carNumber);
            @Cleanup ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            log.error(e);
            return false;
        }
    }
    public void insertMember(MonthlyMemberVO monthlyMemberVO) {
        String sql = "INSERT INTO monthly_member VALUES(?,?,?,?,?)";
        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, monthlyMemberVO.getCarNumber());
            preparedStatement.setString(2, monthlyMemberVO.getDriverName());
            preparedStatement.setString(3, monthlyMemberVO.getPhone());
            preparedStatement.setDate(4, Date.valueOf(LocalDate.now()));
            preparedStatement.setDate(5, Date.valueOf(monthlyMemberVO.getEndDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
    }
    public void updateMember(MonthlyMemberVO monthlyMemberVO) {
        String sql = "UPDATE monthly_member set end_date = ?,start_date = ?, driver_name= ?, phone = ? where car_number = ?";
        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, Date.valueOf(monthlyMemberVO.getEndDate()));
            preparedStatement.setDate( 2, Date.valueOf(monthlyMemberVO.getStartDate()));
            preparedStatement.setString(3, monthlyMemberVO.getDriverName());
            preparedStatement.setString(4, monthlyMemberVO.getPhone());
            preparedStatement.setString(5, monthlyMemberVO.getCarNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
    }
    public void deleteMemberByCarNumber(String carNumber) {
        String sql = "DELETE FROM monthly_member WHERE car_number = ?";
        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, carNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
    }
}
