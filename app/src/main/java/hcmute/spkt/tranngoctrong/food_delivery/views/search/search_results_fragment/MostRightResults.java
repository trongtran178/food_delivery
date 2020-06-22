package hcmute.spkt.tranngoctrong.food_delivery.views.search.search_results_fragment;

import android.location.LocationListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.adapter.RestaurantResultAdapter;
import hcmute.spkt.tranngoctrong.food_delivery.model.Restaurant;
import hcmute.spkt.tranngoctrong.food_delivery.viewmodels.SearchRestaurantResultsViewModel;

public class MostRightResults extends Fragment {

    private RecyclerView restaurantResultRecyclerView;
    private RestaurantResultAdapter restaurantResultAdapter;
    private SearchRestaurantResultsViewModel searchRestaurantResultsViewModel;
    private String keyword;

    public MostRightResults() {

    }


    public MostRightResults(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        restaurantResultAdapter = new RestaurantResultAdapter(this.getContext());

        restaurantResultRecyclerView = getView().findViewById(R.id.restaurant_results_recycler_view);
        restaurantResultRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        restaurantResultRecyclerView.setHasFixedSize(true);
        restaurantResultRecyclerView.setAdapter(restaurantResultAdapter);

        searchRestaurantResultsViewModel = ViewModelProviders.of(this).get(SearchRestaurantResultsViewModel.class);
        searchRestaurantResultsViewModel.init();
        searchRestaurantResultsViewModel.searchRestaurantsByKeyword(keyword);
        searchRestaurantResultsViewModel.getRestaurants().observe(this, new Observer<List<Restaurant>>() {
            @Override
            public void onChanged(List<Restaurant> restaurants) {
                restaurantResultAdapter.setResults(restaurants);
            }
        });
    }


    public void refreshDataWithNewKeywordSearch() {
        searchRestaurantResultsViewModel.searchRestaurantsByKeyword(keyword);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_most_right, container, false);
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
