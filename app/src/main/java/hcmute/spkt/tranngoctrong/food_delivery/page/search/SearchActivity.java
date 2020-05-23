package hcmute.spkt.tranngoctrong.food_delivery.page.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.adapter.RestaurantAdapter;

public class SearchActivity extends AppCompatActivity {

    private Button chooseProvinceButton;
    private SearchView searchTextInput;
    private static final String SEARCH_QUERY_EXTRA = "SEARCH_QUERY_EXTRA";
    private static final int REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        searchTextInput = (SearchView) findViewById(R.id.search_restaurant_view);
        searchTextInput.setOnQueryTextListener(searchViewQueryTextListener);
        RecyclerView restaurantRecyclerView = findViewById(R.id.restaurant_recycler_view);
        restaurantRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        restaurantRecyclerView.setHasFixedSize(true);

        final RestaurantAdapter restaurantAdapter = new RestaurantAdapter(this);

        restaurantRecyclerView.setAdapter(restaurantAdapter);
        try {
            chooseProvinceButton = findViewById(R.id.open_choose_province_button);
            chooseProvinceButton.setOnClickListener(openChooseProvince);
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    private View.OnClickListener openChooseProvince = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Intent intent = new Intent(SearchActivity.this, ChooseProvincesActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        }
    };

    private SearchView.OnQueryTextListener searchViewQueryTextListener = new SearchView.OnQueryTextListener() {

        @Override
        public boolean onQueryTextSubmit(String query) {
            System.out.println(query);
            Intent searchResultIntent = new Intent(getApplicationContext(), SearchResultsActivity.class);
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

            }
        }
    }


}
