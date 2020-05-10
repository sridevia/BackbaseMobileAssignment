package com.sridevi.backbase.mobileassignment.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.sridevi.backbase.mobileassignment.R;
import com.sridevi.backbase.mobileassignment.data.CityData;
import com.sridevi.backbase.mobileassignment.fragments.MapFragment;
import com.sridevi.backbase.mobileassignment.interfaces.ItemClickListener;
import com.sridevi.backbase.mobileassignment.utils.AppConstants;

public class CityListActivity extends AppCompatActivity implements ItemClickListener {

    private MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        if(fragment.getId() == R.id.mapFragment) {
            mapFragment = (MapFragment) fragment;
        }
    }

    @Override
    public void onItemClicked(CityData item) {
        if(mapFragment != null) {
            mapFragment.onItemClicked(item);
        } else {
            launchMapsActivity(item);
        }
    }

    private void launchMapsActivity(CityData item){
        Intent intent = new Intent(this, MapActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstants.CITIES_DATA, item);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
