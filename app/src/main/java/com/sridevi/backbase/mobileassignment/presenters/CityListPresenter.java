package com.sridevi.backbase.mobileassignment.presenters;

import com.sridevi.backbase.mobileassignment.data.CityData;

import java.util.List;

public interface CityListPresenter {
    void getCitiesData();
    void onSuccess(List<CityData> citiesList);
    void onFail();
}
