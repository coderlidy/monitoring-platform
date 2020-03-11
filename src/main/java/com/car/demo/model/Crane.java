package com.car.demo.model;

import java.sql.Timestamp;

public class Crane {

    private Long username;
    private Long carNumber;
    private String carTypeNumber;
    private Timestamp birthday;
    private Timestamp useDay;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;
    private Double nowWeightCount;
    private Double maxWeightCount;
    private Double maxLiftWeight;

    public Double getNowWeightCount() {
        return nowWeightCount;
    }

    public void setNowWeightCount(Double nowWeightCount) {
        this.nowWeightCount = nowWeightCount;
    }

    public Double getMaxWeightCount() {
        return maxWeightCount;
    }

    public void setMaxWeightCount(Double maxWeightCount) {
        this.maxWeightCount = maxWeightCount;
    }

    public Double getMaxLiftWeight() {
        return maxLiftWeight;
    }

    public void setMaxLiftWeight(Double maxLiftWeight) {
        this.maxLiftWeight = maxLiftWeight;
    }

    public String getCarTypeNumber() {
        return carTypeNumber;
    }

    public void setCarTypeNumber(String carTypeNumber) {
        this.carTypeNumber = carTypeNumber;
    }

    public Timestamp getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Long getUsername() {
        return username;
    }

    public void setUsername(Long username) {
        this.username = username;
    }

    public Long getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(Long carNumber) {
        this.carNumber = carNumber;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public Timestamp getUseDay() {
        return useDay;
    }

    public void setUseDay(Timestamp useDay) {
        this.useDay = useDay;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }


}
