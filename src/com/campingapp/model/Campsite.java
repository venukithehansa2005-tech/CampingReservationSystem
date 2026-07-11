package com.campingapp.model;

import java.math.BigDecimal;

public class Campsite {
    private int campsiteId;
    private String siteName;
    private String siteType;
    private BigDecimal pricePerNight;
    private int capacity;
    private String status;

    public Campsite() {
    }

    public Campsite(int campsiteId, String siteName, String siteType, BigDecimal pricePerNight, int capacity, String status) {
        this.campsiteId = campsiteId;
        this.siteName = siteName;
        this.siteType = siteType;
        this.pricePerNight = pricePerNight;
        this.capacity = capacity;
        this.status = status;
    }

    public int getCampsiteId() {
        return campsiteId;
    }

    public void setCampsiteId(int campsiteId) {
        this.campsiteId = campsiteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteType() {
        return siteType;
    }

    public void setSiteType(String siteType) {
        this.siteType = siteType;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}