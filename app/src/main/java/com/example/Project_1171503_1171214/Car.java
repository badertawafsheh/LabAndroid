package com.example.Project_1171503_1171214;

import java.util.ArrayList;

public class Car {
    public static ArrayList<Car> allCars = new ArrayList<Car>();

    private int year ;
    private String make ;
    private String model ;
    private String distance ;
    private double price ;
    private boolean accidents ;
    private boolean offers ;
    public Car() {
    }
    public Car(int year, String make, String model, String distance, double price, boolean accidents, boolean offers) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.distance = distance;
        this.price = price;
        this.accidents = accidents;
        this.offers = offers;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAccidents() {
        return accidents;
    }

    public void setAccidents(boolean accidents) {
        this.accidents = accidents;
    }

    public boolean isOffers() {
        return offers;
    }

    public void setOffers(boolean offers) {
        this.offers = offers;
    }

    @Override
    public String toString() {
        return "car{" +
                "year=" + year +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", distance='" + distance + '\'' +
                ", price=" + price +
                ", accidents=" + accidents +
                ", offers=" + offers +
                '}';
    }
}
