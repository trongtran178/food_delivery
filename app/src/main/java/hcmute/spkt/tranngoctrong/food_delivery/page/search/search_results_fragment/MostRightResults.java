package hcmute.spkt.tranngoctrong.food_delivery.page.search.search_results_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.adapter.RestaurantResultAdapter;

public class MostRightResults extends Fragment {

    RecyclerView restaurantResultRecyclerView;
    RestaurantResultAdapter restaurantResultAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        restaurantResultRecyclerView = (RecyclerView)getView().findViewById(R.id.restaurant_results_recycler_view);
        restaurantResultAdapter = new RestaurantResultAdapter(this.getContext());

        restaurantResultRecyclerView.setAdapter(restaurantResultAdapter);
        restaurantResultRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        restaurantResultRecyclerView.setHasFixedSize(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_most_right, container, false);
    }
}
