package hcmute.spkt.tranngoctrong.food_delivery.views.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.FragmentManager;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.adapter.FoodAdapter;
import hcmute.spkt.tranngoctrong.food_delivery.fragment.RestaurantMapFragment;
import hcmute.spkt.tranngoctrong.food_delivery.model.Food;
import hcmute.spkt.tranngoctrong.food_delivery.model.Restaurant;
import hcmute.spkt.tranngoctrong.food_delivery.model.api.Response;
import hcmute.spkt.tranngoctrong.food_delivery.repositories.RestaurantRepository;
import hcmute.spkt.tranngoctrong.food_delivery.services.Api;

public class RestaurantDetailsActivity extends AppCompatActivity {

    private RecyclerView restaurantFoodsRecyclerView;
    private FoodAdapter foodAdapter;
    private RestaurantMapFragment restaurantMapFragment;
    private RestaurantRepository restaurantRepository;
    private LinearLayout menuLayout;
    private TextView addWifiTextView;
    private ImageButton restaurant_detail_back_button;
    private List<Food> restaurantFoods = new ArrayList<Food>();
//    private Api api;

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


        addWifiTextView = (TextView) findViewById(R.id.add_wifi_text_view);
        addWifiTextView.setOnClickListener(addWifiTextViewListener);
        restaurant_detail_back_button = (ImageButton) findViewById(R.id.restaurant_detail_back_button);
        restaurant_detail_back_button.setOnClickListener(restaurantDetailBackButtonListener);


    }

    private View.OnClickListener menuLayoutClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            final Intent goToMenuDetailIntent = new Intent(RestaurantDetailsActivity.this,
                    MenuDetailsActivity.class);
            startActivity(goToMenuDetailIntent);
        }
    };

    private View.OnClickListener restaurantDetailBackButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RestaurantDetailsActivity.super.finish();
        }
    };


    private View.OnClickListener addWifiTextViewListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(RestaurantDetailsActivity.this);
            builder.setView(LayoutInflater.from(getBaseContext()).inflate(R.layout.dialog_add_wifi, null)).show();
        }
    };

    private List<Food> getRestaurantFoods() {
        List<Food> restaurantFoods = new ArrayList<Food>();
        this.restaurantFoods.add(new Food(1, "Met khung", 50000));
        this.restaurantFoods.add(new Food(1, "Met 160k", 50000));
        this.restaurantFoods.add(new Food(1, "Met nay 4 nguoi", 50000));
        return restaurantFoods;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("RestaurantDetailsActivity Destroyed");
    }


}
