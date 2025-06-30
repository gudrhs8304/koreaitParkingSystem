package com.koreait.koreaitparkingsystem.dao;

import com.koreait.koreaitparkingsystem.util.DBConnection;
import com.koreait.koreaitparkingsystem.vo.CarTypeVO;
import com.koreait.koreaitparkingsystem.vo.MonthlyMemberVO;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class CarTypeDAO {

    public List<CarTypeVO> selectCarType() {
        String sql = "select * from car_type";
        List<CarTypeVO> carTypeVOList = new ArrayList<>();
        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CarTypeVO carTypeVO = CarTypeVO.builder()
                        .code(resultSet.getString("code"))
                        .name(resultSet.getString("name"))
                        .build();
                carTypeVOList.add(carTypeVO);
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return carTypeVOList;
    }

    public void insertCarType(CarTypeVO carTypeVO) {
        String sql = "insert into car_type values(?,?)";

        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, carTypeVO.getCode());
            preparedStatement.setString(2, carTypeVO.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    public void deleteCarType(CarTypeVO carTypeVO) {
        String sql = "delete from car_type where code=?";
        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, carTypeVO.getCode());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            log.error(e);
        }
    }
}
