package com.raghulrider.androidworkshop;

public class Weather {


    private String desc;
    private Double currentTemp;
    private Double currentmin;
    private Double currentmax;


    public Weather(String desc, Double currentTemp, Double currentmin, Double currentmax) {
        this.desc = desc;
        this.currentTemp = currentTemp;
        this.currentmin = currentmin;
        this.currentmax = currentmax;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Double getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(Double currentTemp) {
        this.currentTemp = currentTemp;
    }

    public Double getCurrentmin() {
        return currentmin;
    }

    public void setCurrentmin(Double currentmin) {
        this.currentmin = currentmin;
    }

    public Double getCurrentmax() {
        return currentmax;
    }

    public void setCurrentmax(Double currentmax) {
        this.currentmax = currentmax;
    }
}
