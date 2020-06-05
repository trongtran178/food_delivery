package hcmute.spkt.tranngoctrong.food_delivery.views.main_page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.DisplayMetrics;

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.adapter.FavoriteRestaurantAdapter;

public class MainPageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        RecyclerView favoriteRestaurantRecyclerView = findViewById(R.id.favorite_restaurant_recycler_view);
        favoriteRestaurantRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        favoriteRestaurantRecyclerView.setHasFixedSize(true);

        final FavoriteRestaurantAdapter favoriteRestaurantAdapter = new FavoriteRestaurantAdapter(this);
        favoriteRestaurantRecyclerView.setAdapter(favoriteRestaurantAdapter);
        DisplayMetrics displaymetrics = new DisplayMetrics();
//        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

    }
}
