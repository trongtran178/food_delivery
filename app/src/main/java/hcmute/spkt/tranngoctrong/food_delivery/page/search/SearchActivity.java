package hcmute.spkt.tranngoctrong.food_delivery.page.search;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.adapter.RestaurantAdapter;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        RecyclerView restaurantRecyclerView = findViewById(R.id.restaurant_recycler_view);
        restaurantRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        restaurantRecyclerView.setHasFixedSize(true);

        final RestaurantAdapter restaurantAdapter = new RestaurantAdapter(this);

        restaurantRecyclerView.setAdapter(restaurantAdapter);
    }

    protected void onActivityForResult(int requestCode, int resultCode, Intent data) {

    }

}
