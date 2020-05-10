package com.sridevi.backbase.mobileassignment.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sridevi.backbase.mobileassignment.R;
import com.sridevi.backbase.mobileassignment.adapters.CityListAdapter;
import com.sridevi.backbase.mobileassignment.data.CityData;
import com.sridevi.backbase.mobileassignment.fragments.views.CityListView;
import com.sridevi.backbase.mobileassignment.interfaces.ItemClickListener;
import com.sridevi.backbase.mobileassignment.presenters.impl.CityListPresenterImpl;
import com.sridevi.backbase.mobileassignment.utils.SearchEngine;

import java.util.List;

public class CityListFragment extends Fragment implements CityListView, ItemClickListener, SearchEngine.SearchResultListener {

    private RecyclerView recyclerView;
    private CityListAdapter cityListAdapter;
    private ItemClickListener itemClickListener;
    private ProgressBar progressBar;
    private SearchEngine searchEngine;
    private TextView emptyView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_city_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initViews(view);
        CityListPresenterImpl cityListPresenter = new CityListPresenterImpl(this, getContext());
        cityListPresenter.getCitiesData();
    }

    private void initViews(View view) {
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.recyclerView);
        emptyView = view.findViewById(R.id.emptyView);
        final EditText searchEditText = view.findViewById(R.id.searchEditText);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (searchEngine != null) {
                    recyclerView.scrollToPosition(0);
                    searchEngine.searchAsync(charSequence);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            itemClickListener = (ItemClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement ItemClickListener");
        }
    }

    @Override
    public void setCityList(List<CityData> cityList) {
        cityListAdapter = new CityListAdapter(cityList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(cityListAdapter);
        searchEngine = new SearchEngine(cityList, this);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemClicked(CityData item) {
        itemClickListener.onItemClicked(item);
    }

    @VisibleForTesting
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    public void onSearchResult(List<CityData> citiesFiltered) {
        emptyView.setVisibility(citiesFiltered == null || citiesFiltered.size()== 0 ? View.VISIBLE : View.GONE);
        cityListAdapter.updateSource(citiesFiltered);
    }
}
