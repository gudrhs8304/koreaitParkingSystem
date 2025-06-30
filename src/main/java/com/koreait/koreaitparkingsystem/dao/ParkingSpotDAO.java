package com.koreait.koreaitparkingsystem.dao;

import com.koreait.koreaitparkingsystem.util.DBConnection;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParkingSpotDAO {

    public Integer assignSpot() {
        String sql = "SELECT spot_number FROM parking_spot WHERE is_occupied = FALSE LIMIT 1";
        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
            @Cleanup ResultSet rs = pstmt.executeQuery();
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
            @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, spotNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void releaseSpot(int spotNumber) {
        String sql = "UPDATE parking_spot SET is_occupied = FALSE WHERE spot_number = ?";
        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, spotNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getAvailableSpotCount() {
        String sql = "SELECT COUNT(*) FROM parking_spot WHERE is_occupied = FALSE";
        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
            @Cleanup ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}