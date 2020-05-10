package com.sridevi.backbase.mobileassignment.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sridevi.backbase.mobileassignment.R;
import com.sridevi.backbase.mobileassignment.data.CityData;
import com.sridevi.backbase.mobileassignment.interfaces.ItemClickListener;
import com.sridevi.backbase.mobileassignment.utils.AppConstants;


public class MapFragment extends Fragment implements OnMapReadyCallback, ItemClickListener {

    GoogleMap googleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.cityMap);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        updateMapSettings();
        drawMarker(getCityDataFromBundle());
    }

    private CityData getCityDataFromBundle() {
        return (CityData) getActivity().getIntent().getSerializableExtra(AppConstants.CITIES_DATA);
    }

    private void updateMapSettings() {
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
    }

    private void drawMarker(CityData cityData) {
        //Draw the marker
        if (cityData != null) {
            googleMap.clear();
            LatLng latLng = new LatLng(cityData.getCoordinates().getLatitude(), cityData.getCoordinates().getLongitude());
            googleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(cityData.getName()));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
        }
    }

    @Override
    public void onItemClicked(CityData item) {
        drawMarker(item);
    }
}
