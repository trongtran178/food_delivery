package hcmute.spkt.tranngoctrong.food_delivery.page.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.adapter.RestaurantAdapter;
import hcmute.spkt.tranngoctrong.food_delivery.page.search.ChooseProvinceActivity;
public class SearchActivity extends AppCompatActivity {

    Button chooseProvinceButton;

    private static final int REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        RecyclerView restaurantRecyclerView = findViewById(R.id.restaurant_recycler_view);
        restaurantRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        restaurantRecyclerView.setHasFixedSize(true);

        final RestaurantAdapter restaurantAdapter = new RestaurantAdapter(this);

        restaurantRecyclerView.setAdapter(restaurantAdapter);
        try {
            chooseProvinceButton = findViewById(R.id.choose_province_button);
            chooseProvinceButton.setOnClickListener(openChooseProvince);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    private View.OnClickListener openChooseProvince = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Intent intent = new Intent(SearchActivity.this, ChooseProvinceActivity.class);

            startActivityForResult(intent, REQUEST_CODE);



        }
    };

    protected void onActivityForResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE) {
            if(resultCode == AppCompatActivity.RESULT_OK) {
                final String province = data.getStringExtra(ChooseProvinceActivity.EXTRA_PROVINCE);
                System.out.println(province);
            }
            else {

            }
        }
    }
}
