package com.campingapp.dao;

import com.campingapp.exception.ReservationConflictException;
import com.campingapp.model.Reservation;
import com.campingapp.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    // Check if a campsite is already booked for overlapping dates
    public boolean isCampsiteBooked(int campsiteId, java.sql.Date checkIn, java.sql.Date checkOut) throws SQLException {
        String sql = "SELECT COUNT(*) FROM reservations WHERE campsite_id = ? AND status = 'Booked' " +
                     "AND NOT (check_out <= ? OR check_in >= ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, campsiteId);
            stmt.setDate(2, checkIn);
            stmt.setDate(3, checkOut);
            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                return rs.getInt(1) > 0;
            }
        }
    }

    // CREATE - add a reservation, but block if dates conflict
    public void addReservation(Reservation res) throws SQLException, ReservationConflictException {
        if (isCampsiteBooked(res.getCampsiteId(), res.getCheckIn(), res.getCheckOut())) {
            throw new ReservationConflictException("This campsite is already booked for the selected dates.");
        }
        String sql = "INSERT INTO reservations (customer_id, campsite_id, check_in, check_out, total_cost, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, res.getCustomerId());
            stmt.setInt(2, res.getCampsiteId());
            stmt.setDate(3, res.getCheckIn());
            stmt.setDate(4, res.getCheckOut());
            stmt.setBigDecimal(5, res.getTotalCost());
            stmt.setString(6, res.getStatus());
            stmt.executeUpdate();
        }
    }

    // READ - get all reservations
    public List<Reservation> getAllReservations() throws SQLException {
        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT * FROM reservations";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Reservation r = new Reservation(
                        rs.getInt("reservation_id"),
                        rs.getInt("customer_id"),
                        rs.getInt("campsite_id"),
                        rs.getDate("check_in"),
                        rs.getDate("check_out"),
                        rs.getBigDecimal("total_cost"),
                        rs.getString("status")
                );
                list.add(r);
            }
        }
        return list;
    }

    // UPDATE - e.g. cancel a reservation
    public void updateReservationStatus(int reservationId, String status) throws SQLException {
        String sql = "UPDATE reservations SET status = ? WHERE reservation_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, reservationId);
            stmt.executeUpdate();
        }
    }

    // DELETE - remove a reservation entirely
    public void deleteReservation(int reservationId) throws SQLException {
        String sql = "DELETE FROM reservations WHERE reservation_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reservationId);
            stmt.executeUpdate();
        }
    }
}
