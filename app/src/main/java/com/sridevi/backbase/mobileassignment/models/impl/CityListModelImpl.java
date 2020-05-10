package com.sridevi.backbase.mobileassignment.models.impl;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sridevi.backbase.mobileassignment.data.CityData;
import com.sridevi.backbase.mobileassignment.models.CityListModel;
import com.sridevi.backbase.mobileassignment.presenters.CityListPresenter;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CityListModelImpl implements CityListModel {

    private static final String TAG = CityListModelImpl.class.getSimpleName();
    private final CityListPresenter presenter;
    private final WeakReference<Context> context;

    public CityListModelImpl(CityListPresenter presenter, Context context) {
        this.presenter = presenter;
        this.context = new WeakReference<>(context);
    }

    @Override
    public void getCitiesData() {
        String citiesJsonString = getCitiesDataFromAssets();
        List<CityData> cityDataList =  new Gson().fromJson(citiesJsonString, new TypeToken<ArrayList<CityData>>() {}.getType());
        if (cityDataList == null) throw new AssertionError();
        presenter.onSuccess(cityDataList);

    }

    private String getCitiesDataFromAssets() {
        if (context.get() != null) {
            try {
                AssetManager manager = context.get().getAssets();
                InputStream file = manager.open("cities.json");
                byte[] formArray = new byte[file.available()];
                file.read(formArray);
                file.close();
                return new String(formArray);
            } catch (IOException ex) {
                Log.e(TAG, ex.getLocalizedMessage(), ex);
            }
        }
        return null;
    }


}
