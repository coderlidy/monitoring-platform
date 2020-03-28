package com.car.demo.dto;

import com.car.demo.model.Position;

import java.sql.Timestamp;
import java.util.List;
import java.util.Stack;

public class CranenowDTO {
    private Long id;
    private Long carNumber;
    private List<Position> positions;
    private Double nowWeight;
    private Double maxLiftWeight;
    private String gmtCreate;
    private String gmtModified;

    public Double getMaxLiftWeight() {
        return maxLiftWeight;
    }

    public void setMaxLiftWeight(Double maxLiftWeight) {
        this.maxLiftWeight = maxLiftWeight;
    }

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

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public Double getNowWeight() {
        return nowWeight;
    }

    public void setNowWeight(Double nowWeight) {
        this.nowWeight = nowWeight;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }
}
