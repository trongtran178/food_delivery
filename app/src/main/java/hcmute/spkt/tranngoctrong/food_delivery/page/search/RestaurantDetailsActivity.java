package hcmute.spkt.tranngoctrong.food_delivery.page.search;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
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

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.adapter.FoodAdapter;
import hcmute.spkt.tranngoctrong.food_delivery.fragment.RestaurantMapFragment;
import hcmute.spkt.tranngoctrong.food_delivery.model.Food;
import hcmute.spkt.tranngoctrong.food_delivery.model.api.Response;
import hcmute.spkt.tranngoctrong.food_delivery.services.Api;
import hcmute.spkt.tranngoctrong.food_delivery.services.GetAsyncTask;

public class RestaurantDetailsActivity extends AppCompatActivity {

    private RecyclerView restaurantFoodsRecyclerView;
    private FoodAdapter foodAdapter;
    private RestaurantMapFragment restaurantMapFragment;
    private LinearLayout menuLayout;
    private TextView addWifiTextView;
    private ImageButton restaurant_detail_back_button;
    private List<Food> restaurantFoods = new ArrayList<Food>();
    private Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        api = Api.getInstance();

        setContentView(R.layout.activity_restaurant_detail);

        Response response = api.get("/restaurants");
        System.out.println(response.getResults());

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


}
