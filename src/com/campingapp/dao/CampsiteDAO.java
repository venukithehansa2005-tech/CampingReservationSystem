package com.campingapp.dao;

import com.campingapp.model.Campsite;
import com.campingapp.util.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CampsiteDAO {

    public void addCampsite(Campsite site) throws SQLException {
        String sql = "INSERT INTO campsites (site_name, site_type, price_per_night, capacity, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, site.getSiteName());
            stmt.setString(2, site.getSiteType());
            stmt.setBigDecimal(3, site.getPricePerNight());
            stmt.setInt(4, site.getCapacity());
            stmt.setString(5, site.getStatus());
            stmt.executeUpdate();
        }
    }

    public List<Campsite> getAllCampsites() throws SQLException {
        List<Campsite> list = new ArrayList<>();
        String sql = "SELECT * FROM campsites";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Campsite c = new Campsite(
                        rs.getInt("campsite_id"),
                        rs.getString("site_name"),
                        rs.getString("site_type"),
                        rs.getBigDecimal("price_per_night"),
                        rs.getInt("capacity"),
                        rs.getString("status")
                );
                list.add(c);
            }
        }
        return list;
    }

    public void updateCampsite(Campsite site) throws SQLException {
        String sql = "UPDATE campsites SET site_name = ?, site_type = ?, price_per_night = ?, capacity = ?, status = ? WHERE campsite_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, site.getSiteName());
            stmt.setString(2, site.getSiteType());
            stmt.setBigDecimal(3, site.getPricePerNight());
            stmt.setInt(4, site.getCapacity());
            stmt.setString(5, site.getStatus());
            stmt.setInt(6, site.getCampsiteId());
            stmt.executeUpdate();
        }
    }

    public void deleteCampsite(int campsiteId) throws SQLException {
        String sql = "DELETE FROM campsites WHERE campsite_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, campsiteId);
            stmt.executeUpdate();
        }
    }
}
