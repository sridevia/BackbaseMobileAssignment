package com.sridevi.backbase.mobileassignment.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sridevi.backbase.mobileassignment.R;
import com.sridevi.backbase.mobileassignment.about.AboutActivity;
import com.sridevi.backbase.mobileassignment.data.CityData;
import com.sridevi.backbase.mobileassignment.interfaces.ItemClickListener;

import java.util.List;

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.CityViewHolder> {

    private final ItemClickListener listener;
    private List<CityData> citiesFiltered;
    private List<CityData> cities;

    public CityListAdapter(List<CityData> cities, ItemClickListener listener) {
        this.listener = listener;
        this.cities = cities;
        this.citiesFiltered = cities;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_city_item, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        final CityData cityData = citiesFiltered.get(position);
        holder.cityNameTextView.setText(cityData.getName() + ",");
        holder.countryCodeTextView.setText(cityData.getCountry());
        holder.latitudeTextView.setText(String.valueOf(cityData.getCoordinates().getLatitude()));
        holder.longitudeTextView.setText(String.valueOf(cityData.getCoordinates().getLongitude()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(cityData);
            }
        });
    }

    @Override
    public int getItemCount() {
        return citiesFiltered.size();
    }

    public void updateSource(List<CityData> citiesFiltered) {
        this.citiesFiltered = citiesFiltered;
        notifyDataSetChanged();
    }

    public static class CityViewHolder extends RecyclerView.ViewHolder {

        TextView cityNameTextView;
        TextView countryCodeTextView;
        TextView longitudeTextView;
        TextView latitudeTextView;
        ImageView infoImageView;

        public CityViewHolder(@NonNull View itemView) {
            super(itemView);
            cityNameTextView = itemView.findViewById(R.id.cityNameTextView);
            countryCodeTextView = itemView.findViewById(R.id.countryCodeTextView);
            longitudeTextView = itemView.findViewById(R.id.longitudeTextView);
            latitudeTextView = itemView.findViewById(R.id.latitudeTextView);
            infoImageView = itemView.findViewById(R.id.infoImageView);
            infoImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    launchAboutActivity();
                }
            });
        }

        private void launchAboutActivity() {
            itemView.getContext().startActivity(new Intent(itemView.getContext(), AboutActivity.class));
        }
    }

}
