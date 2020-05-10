package com.sridevi.backbase.mobileassignment.utils;

import android.os.Handler;

import androidx.annotation.NonNull;

import com.sridevi.backbase.mobileassignment.data.CityData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchEngine {

    private SearchResultListener searchResultListener;
    private Map<Character, List<CityData>> citiesBaseMap;
    private List<CityData> cities;
    private List<CityData> citiesFiltered;
    private Map<String, List<CityData>> citiesTempMap = new HashMap<>();

    public SearchEngine(@NonNull List<CityData> cities, SearchResultListener searchResultListener) {
        this.cities = cities;
        Collections.sort(this.cities);
        this.searchResultListener = searchResultListener;
        prepareSearchBase();
    }

    /**
     * Prepares the map with the first char of the city to make the search process faster.
     * This method populates the citiesBaseMap,
     * so that search happens on the specific bucket rather than full list cities.
     */
    private void prepareSearchBase() {
        citiesBaseMap = new HashMap<>();
        for (CityData cityData : cities) {
            char firstChar = Character.toLowerCase(cityData.getName().charAt(0));
            if (citiesBaseMap.containsKey(firstChar)) {
                citiesBaseMap.get(firstChar).add(cityData);
            } else {
                List<CityData> list = new ArrayList<>();
                list.add(cityData);
                citiesBaseMap.put(firstChar, list);
            }
        }
    }

    public void searchAsync(CharSequence charSequence) {
        new Handler().postDelayed(() -> {
            if (searchResultListener != null) {
                searchResultListener.onSearchResult(searchSync(charSequence));
            }
        }, 100);
    }

    public List<CityData> searchSync(CharSequence charSequence) {
        String charString = charSequence.toString().toLowerCase();
        // If search field is empty, take the full list of cities.
        List<CityData> citiesFiltered = null;
        if (charString.isEmpty()) {
            citiesFiltered = cities;
            citiesTempMap.clear();
        } else if (citiesTempMap.containsKey(charString)) {
            // Search if there is any entry in the map with the given Key.
            citiesFiltered = citiesTempMap.get(charString);
        } else {
            // if input is only one letter, read from the base map.
            if (charString.length() == 1) {
                if (citiesBaseMap.containsKey(charString.charAt(0))) {
                    //if input char found in map, take it..
                    citiesFiltered = citiesBaseMap.get(charString.charAt(0));
                } else {
                    //Input char doesn't found in map. ie; input is invalid..
                    citiesFiltered = new ArrayList<>();
                }
            } else {
                // if input is more than one char, then search for the result in previous result.
                // Reason: Current result must be a sub set of the previous result.
                // Let's say if user search for "abc", the result must be there for "ab"(if not available for "a").
                List<CityData> filteredList = new ArrayList<>();
                for (CityData row : this.citiesFiltered) {
                    if (row.getName().toLowerCase().startsWith(charString)) {
                        filteredList.add(row);
                    }
                }
                citiesFiltered = filteredList;
                //push the result into map, so that if user remove the chars at the end,
                // we can read from the temp map rather than performing search again.
                citiesTempMap.put(charString, filteredList);
            }
        }
        this.citiesFiltered = citiesFiltered;
        return citiesFiltered;
    }

    public interface SearchResultListener {
        void onSearchResult(List<CityData> citiesFiltered);
    }
}
