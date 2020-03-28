package com.car.demo.dto;

public class AllPositionDTO {
    private Double lat;
    private Double lng;
    private String styleId = "circle";
    public AllPositionDTO(Double lng,Double lat){
        this.lng=lng;
        this.lat=lat;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getStyleId() {
        return styleId;
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }
}
