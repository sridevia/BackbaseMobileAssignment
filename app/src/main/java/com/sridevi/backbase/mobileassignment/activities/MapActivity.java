package com.sridevi.backbase.mobileassignment.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.sridevi.backbase.mobileassignment.R;
import com.sridevi.backbase.mobileassignment.data.CityData;
import com.sridevi.backbase.mobileassignment.fragments.MapFragment;
import com.sridevi.backbase.mobileassignment.utils.AppConstants;

public class MapActivity extends AppCompatActivity {
    CityData cityData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        cityData = (CityData) getIntent().getSerializableExtra(AppConstants.CITIES_DATA);
        setActionBar();
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstants.CITIES_DATA, cityData);
        MapFragment mapFragment = new MapFragment();
        mapFragment.setArguments(bundle);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.mapFragment, mapFragment);
        transaction.commit();
    }

    private void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(cityData.getName() + ", " + cityData.getCountry());
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
