package hcmute.spkt.tranngoctrong.food_delivery.views.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.adapter.RestaurantAdapter;
import hcmute.spkt.tranngoctrong.food_delivery.model.Restaurant;
import hcmute.spkt.tranngoctrong.food_delivery.viewmodels.SearchRestaurantViewModel;

public class SearchRestaurantActivity extends AppCompatActivity {

    private RestaurantAdapter restaurantAdapter;
    private Button chooseProvinceButton;
    private ProgressBar searchRestaurantProgressBar;
    private SearchView searchTextInput;
    private SearchRestaurantViewModel searchRestaurantViewModel;
    private RecyclerView restaurantRecyclerView;

    private static final String SEARCH_QUERY_EXTRA = "SEARCH_QUERY_EXTRA";
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        chooseProvinceButton = (Button) findViewById(R.id.open_choose_province_button);
        searchTextInput = (SearchView) findViewById(R.id.search_restaurant_view);
        searchRestaurantProgressBar = (ProgressBar) findViewById(R.id.search_restaurant_progress_bar);
        restaurantRecyclerView = (RecyclerView) findViewById(R.id.restaurant_recycler_view);

        restaurantAdapter = new RestaurantAdapter(this);

        restaurantRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        restaurantRecyclerView.setHasFixedSize(true);
        restaurantRecyclerView.setAdapter(restaurantAdapter);


        searchTextInput.setOnQueryTextListener(searchViewQueryTextListener);
        chooseProvinceButton.setOnClickListener(openChooseProvince);

    }

    @Override
    protected void onStart() {
        super.onStart();

        searchRestaurantProgressBar.setVisibility(View.VISIBLE);
        restaurantRecyclerView.setVisibility(View.GONE);

        searchRestaurantViewModel = ViewModelProviders.of(this).get(SearchRestaurantViewModel.class);
        searchRestaurantViewModel.init();
        searchRestaurantViewModel.getRestaurants().observe(this, new Observer<List<Restaurant>>() {
            @Override
            public void onChanged(List<Restaurant> restaurants) {
                restaurantAdapter.setResults(restaurants);
            }
        });

        searchRestaurantProgressBar.setVisibility(View.GONE);
        restaurantRecyclerView.setVisibility(View.VISIBLE);
    }

    private View.OnClickListener openChooseProvince = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Intent intent = new Intent(SearchRestaurantActivity.this, ChooseProvincesActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        }
    };

    private SearchView.OnQueryTextListener searchViewQueryTextListener = new SearchView.OnQueryTextListener() {

        @Override
        public boolean onQueryTextSubmit(String query) {
            System.out.println(query);
            Intent searchResultIntent = new Intent(getApplicationContext(), SearchRestaurantResultsActivity.class);
            searchResultIntent.putExtra(SEARCH_QUERY_EXTRA, query);
            startActivity(searchResultIntent);

            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                final String province = data.getStringExtra(ChooseProvincesActivity.EXTRA_PROVINCE_SELECTED);
                chooseProvinceButton.setText(province);
                System.out.println(province);
            } else {
                // DO NOTHING
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("SearchActivity stopped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("SearchActivity Destroyed");
    }
}
