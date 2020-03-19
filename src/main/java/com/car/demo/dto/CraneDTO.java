package com.car.demo.dto;


public class CraneDTO {
    private Long id;
    private Long username;
    private String name;
    private Long carNumber;
    private String carTypeNumber;
    private String birthday;
    private String useDay;
    private String gmtCreate;
    private String gmtModified;
    private Double nowWeightCount;
    private Double maxWeightCount;
    private Double maxLiftWeight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getUseDay() {
        return useDay;
    }

    public void setUseDay(String useDay) {
        this.useDay = useDay;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

}
