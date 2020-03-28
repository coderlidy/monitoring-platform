package com.car.demo.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Stack;

public class Cranenow {
    private Long id;
    private Long carNumber;
    private String positions;
    private Double nowWeight;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(Long carNumber) {
        this.carNumber = carNumber;
    }

    public String getPositions() {
        return positions;
    }

    public void setPositions(String positions) {
        this.positions = positions;
    }

    public Double getNowWeight() {
        return nowWeight;
    }

    public void setNowWeight(Double nowWeight) {
        this.nowWeight = nowWeight;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = gmtModified;
    }
}
