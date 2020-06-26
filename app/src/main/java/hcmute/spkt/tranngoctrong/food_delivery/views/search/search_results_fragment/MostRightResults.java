package hcmute.spkt.tranngoctrong.food_delivery.views.search.search_results_fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.adapter.RestaurantResultAdapter;
import hcmute.spkt.tranngoctrong.food_delivery.model.Restaurant;
import hcmute.spkt.tranngoctrong.food_delivery.viewmodels.SearchRestaurantResultsViewModel;

public class MostRightResults extends Fragment {

    private RecyclerView restaurantResultRecyclerView;
    private RestaurantResultAdapter restaurantResultAdapter;
    private LinearLayout mostRightLoadingLayout;
    private SearchRestaurantResultsViewModel searchRestaurantResultsViewModel;
    private String keyword;

    public MostRightResults() {

    }

    public MostRightResults(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_most_right, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        restaurantResultAdapter = new RestaurantResultAdapter(this.getContext());

        mostRightLoadingLayout = getView().findViewById(R.id.most_right_loading_layout);
        restaurantResultRecyclerView = getView().findViewById(R.id.restaurant_results_recycler_view);

        restaurantResultRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        restaurantResultRecyclerView.setHasFixedSize(true);
        restaurantResultRecyclerView.setAdapter(restaurantResultAdapter);

        searchRestaurantResultsViewModel = ViewModelProviders.of(this).get(SearchRestaurantResultsViewModel.class);
        searchRestaurantResultsViewModel.init();
        searchRestaurantResultsViewModel.searchRestaurantsByKeyword(keyword, FragmentType.MOST_RIGHT);
        searchRestaurantResultsViewModel.getRestaurants().observe(this, new Observer<List<Restaurant>>() {
            @Override
            public void onChanged(final List<Restaurant> restaurants) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(64 + ", mostright ");
                        mostRightLoadingLayout.setVisibility(View.GONE);
                        restaurantResultAdapter.setResults(restaurants);
                        restaurantResultRecyclerView.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }

    public void refreshDataWithNewKeywordSearch() {
        searchRestaurantResultsViewModel.searchRestaurantsByKeyword(keyword, FragmentType.MOST_RIGHT);
    }


    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
