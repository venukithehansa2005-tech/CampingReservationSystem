package com.campingapp.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Reservation {
    private int reservationId;
    private int customerId;
    private int campsiteId;
    private Date checkIn;
    private Date checkOut;
    private BigDecimal totalCost;
    private String status;

    public Reservation() {
    }

    public Reservation(int reservationId, int customerId, int campsiteId, Date checkIn, Date checkOut, BigDecimal totalCost, String status) {
        this.reservationId = reservationId;
        this.customerId = customerId;
        this.campsiteId = campsiteId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalCost = totalCost;
        this.status = status;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCampsiteId() {
        return campsiteId;
    }

    public void setCampsiteId(int campsiteId) {
        this.campsiteId = campsiteId;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
