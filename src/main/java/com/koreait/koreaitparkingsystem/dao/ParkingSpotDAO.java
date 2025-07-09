package com.koreait.koreaitparkingsystem.dao;

import com.koreait.koreaitparkingsystem.util.DBConnection;
import com.koreait.koreaitparkingsystem.vo.ParkingSpotVO;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
            @Cleanup ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ParkingSpotVO parkingSpotVO = ParkingSpotVO.builder()
                        .spotNumber(Integer.parseInt(rs.getString("spot_number")))
                        .isOccupied(rs.getBoolean("is_occupied"))
                        .build();
                parkingSpots.add(parkingSpotVO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return parkingSpots;
    }

    public List<ParkingSpotVO> selectAllParkingSpotsWithCarNumber() {
        String sql = "SELECT ps.spot_number, ps.is_occupied, pl.car_number " +
                     "FROM parking_spot ps " +
                     "LEFT JOIN parking_log pl " +
                     "ON ps.spot_number = pl.parking_spot AND pl.out_time IS NULL order by ps.spot_number asc";
        List<ParkingSpotVO> parkingSpots = new ArrayList<>();

        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            @Cleanup ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ParkingSpotVO parkingSpotVO = ParkingSpotVO.builder()
                        .spotNumber(rs.getInt("spot_number"))
                        .isOccupied(rs.getBoolean("is_occupied"))
                        .carNumber(rs.getString("car_number"))
                        .build();
                parkingSpots.add(parkingSpotVO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return parkingSpots;
    }
}