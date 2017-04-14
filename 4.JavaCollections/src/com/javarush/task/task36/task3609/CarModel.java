package com.javarush.task.task36.task3609;

public class CarModel {
    private String brand;
    private String model;
    private int speed;
    private int maxSpeed;

    public CarModel(final String brand, final String model, final int speed, final int maxSpeed) {
        this.brand = brand;
        this.model = model;
        this.speed = speed;
        this.maxSpeed = maxSpeed;
    }

    public String getModel() {
        return model;
    }

    public void setModel(final String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(final String brand) {
        this.brand = brand;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(final int speed) {
        this.speed = speed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(final int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

}