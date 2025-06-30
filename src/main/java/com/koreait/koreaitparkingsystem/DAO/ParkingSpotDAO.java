package com.koreait.koreaitparkingsystem.DAO;

import com.koreait.koreaitparkingsystem.VO.ParkingSpotVO;
import com.koreait.koreaitparkingsystem.datebase.DBConnection;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.checkerframework.checker.units.qual.C;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ParkingSpotDAO {
    // 모든 주차 공간과 번호를 가져옴
    static List<ParkingSpotVO> findAll() {
        List<ParkingSpotVO> parkingSpots = new ArrayList<>();
        String sql = "SELECT spot_number, is_occupied FROM parking_spot";
        try {
            @Cleanup Connection connection = DBConnection.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ParkingSpotVO parkingSpotVO = ParkingSpotVO.builder()
                        .spot_number(resultSet.getInt("spot_number"))
                        .is_occupied(resultSet.getBoolean("is_occupied")).build();
                parkingSpots.add(parkingSpotVO);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return parkingSpots;
    }

    // 주차공간에서 특정 번호를 받아서 공간 여부 알기 위해
    public ParkingSpotVO findById(int spot_number) {
        String sql = "SELECT * FROM parking_spot WHERE spot_number = ?";
        try {
            @Cleanup Connection connection = DBConnection.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, spot_number);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return ParkingSpotVO.builder()
                        .spot_number(resultSet.getInt(spot_number)).build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void updateParkingSpot(int spot_number, boolean is_occupied) {
        String sql = "UPDATE parking_spot SET is_occupied = ? WHERE spot_number = ?";

        try {
            @Cleanup Connection connection = DBConnection.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, spot_number);
            preparedStatement.setBoolean(2, is_occupied);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
