package com.sridevi.backbase.mobileassignment.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CoordinatesData implements Serializable {

    @SerializedName("lat")
    private float latitude;

    @SerializedName("lon")
    private float longitude;

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
