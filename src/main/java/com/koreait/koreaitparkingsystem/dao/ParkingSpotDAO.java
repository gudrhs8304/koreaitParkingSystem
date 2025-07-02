package com.koreait.koreaitparkingsystem.dao;

import com.koreait.koreaitparkingsystem.util.DBConnection;
import com.koreait.koreaitparkingsystem.vo.ParkingSpotVO;
import lombok.Cleanup;
import org.eclipse.tags.shaded.org.apache.bcel.generic.INSTANCEOF;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public enum ParkingSpotDAO {
    INSTANCE;

    public Integer assignSpot() {
        String sql = "SELECT spot_number FROM parking_spot WHERE is_occupied = FALSE LIMIT 1";
        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            @Cleanup ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int spot = rs.getInt("spot_number");
                occupySpot(spot);
                return spot;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public void occupySpot(int spotNumber) {
        String sql = "UPDATE parking_spot SET is_occupied = TRUE WHERE spot_number = ?";
        try {
            @Cleanup Connection conn = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, spotNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void releaseSpot(int spotNumber) {
        String sql = "UPDATE parking_spot SET is_occupied = FALSE WHERE spot_number = ?";
        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, spotNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getAvailableSpotCount() {
        String sql = "SELECT COUNT(*) FROM parking_spot WHERE is_occupied = FALSE";
        try {
            @Cleanup Connection conn = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = conn.prepareStatement(sql);
            @Cleanup ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public List<ParkingSpotVO> selectAllParkingSpots() {
        String sql = "SELECT * FROM parking_spot";
        List<ParkingSpotVO> parkingSpots = new ArrayList<>();

        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ParkingSpotVO parkingSpotVO = ParkingSpotVO.builder()
                        .spotNumber(Integer.parseInt(resultSet.getString("spot_number")))
                        .isOccupied(resultSet.getBoolean("is_occupied"))
                        .build();
                parkingSpots.add(parkingSpotVO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return parkingSpots;
    }

    }