package hcmute.spkt.tranngoctrong.food_delivery.views.search;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.adapter.FoodAdapter;
import hcmute.spkt.tranngoctrong.food_delivery.adapter.MenuDetailsAdapter;
import hcmute.spkt.tranngoctrong.food_delivery.model.Food;
import hcmute.spkt.tranngoctrong.food_delivery.model.FoodCategory;
import hcmute.spkt.tranngoctrong.food_delivery.viewmodels.RestaurantDetailsViewModel;

public class MenuDetailsActivity extends AppCompatActivity {

    private ExpandableListView menuDetailsExpandableListView;
    private ExpandableListAdapter menuDetailsExpandableListAdapter;
    private RecyclerView restaurantFoodsRecyclerView;
    private List<String> itemsMenuGroup;
    private HashMap<String, List<Food>> itemsMenuChild;
    private Button menuFoodsButton, menuImagesButton;
    private ImageButton menu_detail_back_button;
    private TextView restaurantName;
    private RestaurantDetailsViewModel restaurantDetailsViewModel;
    private FoodAdapter foodAdapter;
    private static final String RESTAURANT_ID_EXTRA = "RESTAURANT_ID_EXTRA";
    private static final String RESTAURANT_NAME__EXTRA = "RESTAURANT_NAME__EXTRA";
    private static final String FOOD_CATEGORIES_EXTRA = "FOOD_CATEGORIES_EXTRA";
    private String restaurantId;

    public MenuDetailsActivity() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_details);

        restaurantName = findViewById(R.id.menu_details_restaurant_name_text_view);
        menuDetailsExpandableListView = findViewById(R.id.menu_details_expandable_list_view);
        restaurantFoodsRecyclerView = findViewById(R.id.menu_details_food_images);

        menu_detail_back_button = findViewById(R.id.menu_detail_back_button);
        menuFoodsButton = findViewById(R.id.menu_foods_button);
        menuImagesButton = findViewById(R.id.menu_images_button);

        restaurantName.setText(getIntent().getStringExtra(RESTAURANT_NAME__EXTRA));

        restaurantFoodsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        foodAdapter = new FoodAdapter(this);

        restaurantId = getIntent().getStringExtra(RESTAURANT_ID_EXTRA);
        restaurantDetailsViewModel = ViewModelProviders.of(this).get(RestaurantDetailsViewModel.class);
        restaurantDetailsViewModel.init(restaurantId);
        restaurantDetailsViewModel.getFoodCategoriesByRestaurant(restaurantId);

        restaurantDetailsViewModel.getFoodCategories().observe(this, new Observer<List<FoodCategory>>() {
            @Override
            public void onChanged(List<FoodCategory> foodCategoriesResult) {
                itemsMenuChild = getData(foodCategoriesResult);
                itemsMenuGroup = new ArrayList<>(itemsMenuChild.keySet());

                menuDetailsExpandableListAdapter = new MenuDetailsAdapter(MenuDetailsActivity.this, itemsMenuGroup, itemsMenuChild);

                menuDetailsExpandableListView.setAdapter(menuDetailsExpandableListAdapter);
                menuDetailsExpandableListView.expandGroup(0);

                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                int width = metrics.widthPixels;

                menuDetailsExpandableListView.setIndicatorBounds(width - getDipsFromPixel(50.0f), width - getDipsFromPixel(10.0f));

                foodAdapter.setRestaurantFoodCategories(foodCategoriesResult);
                restaurantFoodsRecyclerView.setAdapter(foodAdapter);

            }
        });

        menu_detail_back_button.setOnClickListener(menuDetailBackButtonClickListener);
        menuFoodsButton.setOnClickListener(onMenuImagesButtonClick);
        menuImagesButton.setOnClickListener(onMenuFoodsButtonClick);

    }

    private int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

    private HashMap<String, List<Food>> getData(List<FoodCategory> foodCategories) {

        HashMap<String, List<Food>> data = new HashMap<String, List<Food>>();

        for (FoodCategory foodCategory : foodCategories) {
            data.put(foodCategory.getName(), foodCategory.getFoods());
        }
        return data;
    }

    private View.OnClickListener onMenuImagesButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            menuImagesButton.setBackgroundResource(R.drawable.btn_null);
            menuFoodsButton.setBackgroundResource(android.R.drawable.btn_default);
            handleSwitchTab(false);
        }
    };

    private View.OnClickListener onMenuFoodsButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            menuImagesButton.setBackgroundResource(android.R.drawable.btn_default);
            menuFoodsButton.setBackgroundResource(R.drawable.btn_null);
            handleSwitchTab(true);
        }
    };

    View.OnClickListener menuDetailBackButtonClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            MenuDetailsActivity.super.finish();
        }
    };

    private void handleSwitchTab(boolean isImagesTab) {
        if (isImagesTab) {
            restaurantFoodsRecyclerView.setVisibility(View.VISIBLE);
            menuDetailsExpandableListView.setVisibility(View.GONE);
        } else {
            restaurantFoodsRecyclerView.setVisibility(View.GONE);
            menuDetailsExpandableListView.setVisibility(View.VISIBLE);
        }
    }

}
