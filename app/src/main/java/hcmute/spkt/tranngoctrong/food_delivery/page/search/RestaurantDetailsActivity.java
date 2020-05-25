package hcmute.spkt.tranngoctrong.food_delivery.page.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.FragmentManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.adapter.FoodAdapter;
import hcmute.spkt.tranngoctrong.food_delivery.fragment.RestaurantMapFragment;
import hcmute.spkt.tranngoctrong.food_delivery.model.Food;

public class RestaurantDetailsActivity extends AppCompatActivity {

    private RecyclerView restaurantFoodsRecyclerView;
    private FoodAdapter foodAdapter;
    private RestaurantMapFragment restaurantMapFragment;
    private LinearLayout menuLayout;

    private List<Food> restaurantFoods = new ArrayList<Food>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        restaurantFoodsRecyclerView = findViewById(R.id.restaurant_foods_recycler_view);
        menuLayout = findViewById(R.id.menu_layout);
        restaurantFoods = getRestaurantFoods();
        foodAdapter = new FoodAdapter(this, restaurantFoods);
        restaurantFoodsRecyclerView.setAdapter(foodAdapter);
        restaurantFoodsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        restaurantFoodsRecyclerView.stopNestedScroll();
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        this.restaurantMapFragment = (RestaurantMapFragment) fragmentManager.findFragmentById(R.id.restaurant_map_fragment);
        View restaurantMapView = this.restaurantMapFragment.getView();
        menuLayout.setOnClickListener(menuLayoutClickListener);
        restaurantMapView.setAlpha(0.25f);
    }

    private View.OnClickListener menuLayoutClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            final Intent goToMenuDetailIntent = new Intent(RestaurantDetailsActivity.this,
                    MenuDetailsActivity.class);
            startActivity(goToMenuDetailIntent);
        }
    };

    private List<Food> getRestaurantFoods() {
        List<Food> restaurantFoods = new ArrayList<Food>();
        this.restaurantFoods.add(new Food(1, "Met khung", 50000));
        this.restaurantFoods.add(new Food(1, "Met 160k", 50000));
        this.restaurantFoods.add(new Food(1, "Met nay 4 nguoi", 50000));

        return restaurantFoods;
    }

}
