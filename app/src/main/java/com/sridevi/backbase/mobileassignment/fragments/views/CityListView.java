package com.sridevi.backbase.mobileassignment.fragments.views;

import com.sridevi.backbase.mobileassignment.data.CityData;

import java.util.List;

public interface CityListView {

    void setCityList(List<CityData> citiesList);
    void showProgress();
    void hideProgress();
}
