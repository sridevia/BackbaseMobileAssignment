package com.sridevi.backbase.mobileassignment.data;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CityData implements Comparable<CityData>, Serializable {

    @SerializedName("country")
    private String country;

    @SerializedName("name")
    private String name;

    @SerializedName("_id")
    private int id;

    @NonNull
    @SerializedName("coord")
    private CoordinatesData coordinates;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CoordinatesData getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(CoordinatesData coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public int compareTo(CityData cityData) {
        return (this.getName()+this.getCountry()).compareTo(cityData.getName()+cityData.getCountry());
    }
}
