package com.sridevi.backbase.mobileassignment.presenters.impl;

import android.content.Context;
import android.os.Handler;

import com.sridevi.backbase.mobileassignment.data.CityData;
import com.sridevi.backbase.mobileassignment.fragments.views.CityListView;
import com.sridevi.backbase.mobileassignment.models.impl.CityListModelImpl;
import com.sridevi.backbase.mobileassignment.presenters.CityListPresenter;

import java.lang.ref.WeakReference;
import java.util.List;

public class CityListPresenterImpl implements CityListPresenter {

    private final WeakReference<CityListView> cityListView;
    private final WeakReference<Context> context;
    private final CityListModelImpl cityListModel;

    public CityListPresenterImpl(CityListView cityListView, Context context) {
        this.cityListView = new WeakReference<>(cityListView);
        this.context = new WeakReference<>(context);
        cityListModel = new CityListModelImpl(this, context);
    }

    @Override
    public void getCitiesData() {
        if(cityListView.get() != null) {
            cityListView.get().showProgress();
        }
        new Handler().postDelayed(() -> cityListModel.getCitiesData(), 1000);
    }

    @Override
    public void onSuccess(List<CityData> citiesList) {
        CityListView cityListView = this.cityListView.get();
        if(cityListView != null) {
            cityListView.hideProgress();
            cityListView.setCityList(citiesList);
        }
    }

    @Override
    public void onFail() {

    }
}
