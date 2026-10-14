package com.innovatecksolutions.model;

import java.math.BigDecimal;

public class Car {
//    @Id
    private String carId;
    private String registrationNumber;
    private BigDecimal pricePerDay;
    private String brand;
    private String carType;

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId='" + carId + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", pricePerDay=" + pricePerDay +
                ", brand=" + brand +
                ", type='" + carType + '\'' +
                '}';
    }
}
