package hcmute.spkt.tranngoctrong.food_delivery.views.search;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.adapter.MenuDetailsAdapter;
import hcmute.spkt.tranngoctrong.food_delivery.model.Food;
import hcmute.spkt.tranngoctrong.food_delivery.model.FoodCategory;

public class MenuDetailsActivity extends AppCompatActivity {

    private ExpandableListView menuDetailsExpandableListView;
    private ExpandableListAdapter menuDetailsExpandableListAdapter;
    private List<String> itemsMenuGroup;
    private ArrayList<FoodCategory> foodCategories;
    private HashMap<String, List<Food>> itemsMenuChild;
    private ImageButton menu_detail_back_button;
    private TextView restaurantName;
    private static final String RESTAURANT_NAME__EXTRA = "RESTAURANT_NAME__EXTRA";
    private static final String FOOD_CATEGORIES_EXTRA = "FOOD_CATEGORIES_EXTRA";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_details);
        restaurantName = findViewById(R.id.menu_details_restaurant_name_text_view);
        menu_detail_back_button = findViewById(R.id.menu_detail_back_button);
        menuDetailsExpandableListView = findViewById(R.id.menu_details_expandable_list_view);

        foodCategories = (ArrayList<FoodCategory>) getIntent().getSerializableExtra(FOOD_CATEGORIES_EXTRA);
        itemsMenuChild = getData(foodCategories);
        itemsMenuGroup = new ArrayList<>(itemsMenuChild.keySet());

        menuDetailsExpandableListAdapter = new MenuDetailsAdapter(this, itemsMenuGroup, itemsMenuChild);
        menuDetailsExpandableListView.setAdapter(menuDetailsExpandableListAdapter);
        menuDetailsExpandableListView.expandGroup(0);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;

        menuDetailsExpandableListView.setIndicatorBounds(width - getDipsFromPixel(50.0f), width - getDipsFromPixel(10.0f));

        restaurantName.setText(getIntent().getStringExtra(RESTAURANT_NAME__EXTRA));
        menu_detail_back_button.setOnClickListener(menuDetailBackButtonClickListener);

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

    View.OnClickListener menuDetailBackButtonClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            MenuDetailsActivity.super.finish();
        }
    };
}
