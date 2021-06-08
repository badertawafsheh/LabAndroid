package com.example.Project_1171503_1171214;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class carJsonParser {
    public static  List<Car> cars;

    public static List<Car> getObjectFromJason(String jason) {
        try {
            JSONArray jsonArray = new JSONArray(jason);
            cars = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject = (JSONObject) jsonArray.get(i);
                Car car = new Car();

                car.setYear(jsonObject.getInt("year"));
                car.setMake(jsonObject.getString("make"));
                car.setModel(jsonObject.getString("model"));
                car.setDistance(jsonObject.getString("distance"));
                car.setPrice(jsonObject.getDouble("price"));
                car.setAccidents(jsonObject.getBoolean("accidents"));
                car.setOffers(jsonObject.getBoolean("offers"));

                cars.add(car);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return cars;
    }
}
