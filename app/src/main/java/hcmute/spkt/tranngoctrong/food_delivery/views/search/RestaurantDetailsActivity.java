package hcmute.spkt.tranngoctrong.food_delivery.views.search;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import hcmute.spkt.tranngoctrong.food_delivery.FoodDeliveryApplication;
import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.adapter.FoodAdapter;
import hcmute.spkt.tranngoctrong.food_delivery.adapter.RestaurantImageAdapter;
import hcmute.spkt.tranngoctrong.food_delivery.fragment.RestaurantMapFragment;
import hcmute.spkt.tranngoctrong.food_delivery.model.FoodCategory;
import hcmute.spkt.tranngoctrong.food_delivery.model.Restaurant;
import hcmute.spkt.tranngoctrong.food_delivery.model.Wifi;
import hcmute.spkt.tranngoctrong.food_delivery.utils.UpdateWifiPasswordDialog;
import hcmute.spkt.tranngoctrong.food_delivery.viewmodels.RestaurantDetailsViewModel;

public class RestaurantDetailsActivity extends AppCompatActivity {

    private RecyclerView restaurantImagesRecyclerView;
    private FoodAdapter foodAdapter;
    private RestaurantImageAdapter restaurantImageAdapter;
    private Restaurant restaurant;
    private RestaurantMapFragment restaurantMapFragment;
    private LinearLayout menuLayout;
    private Button contactButton;
    private ImageButton restaurant_detail_back_button;
    private FragmentManager fragmentManager;
    private RestaurantDetailsViewModel restaurantDetailsViewModel;
    private View restaurantMapView;
    private Location currentLocation, restaurantLocation;
    private TextView wifiNameTextView,
            wifiPasswordTextView,
            nameTextView,
            provinceTextView,
            addressTextView,
            distanceFromUserTextView,
            openCloseTextView,
            openCloseTimeTextView,
            typeTextView;

    private static final String RESTAURANT_ID_EXTRA = "RESTAURANT_ID_EXTRA";
    private static final String RESTAURANT_NAME__EXTRA = "RESTAURANT_NAME__EXTRA";
    private static final String FOOD_CATEGORIES_EXTRA = "FOOD_CATEGORIES_EXTRA";

    protected Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
        openCloseTextView = findViewById(R.id.restaurant_open_close_text_view);
        openCloseTimeTextView = findViewById(R.id.restaurant_open_close_time_text_view);
        addressTextView = findViewById(R.id.restaurant_detail_address_text_view);
        distanceFromUserTextView = findViewById(R.id.restaurant_detail_distance_from_user_text_view);
        wifiNameTextView = findViewById(R.id.restaurant_detail_wifi_name_text_view);
        wifiPasswordTextView = findViewById(R.id.restaurant_detail_wifi_password_text_view);
        nameTextView = findViewById(R.id.restaurant_detail_name_text_view);
        provinceTextView = findViewById(R.id.restaurant_detail_province_text_view);
        restaurant_detail_back_button = findViewById(R.id.restaurant_detail_back_button);
        restaurantImagesRecyclerView = findViewById(R.id.restaurant_images_recycler_view);
        menuLayout = findViewById(R.id.menu_layout);
        contactButton = findViewById(R.id.contact_restaurant_button);
        typeTextView = findViewById(R.id.restaurant_detail_type_text_view);
        restaurant = getIntent().getParcelableExtra("restaurant");

        restaurantLocation = new Location("restaurantLocation");
        restaurantLocation.setLatitude(restaurant.getLatitude());
        restaurantLocation.setLongitude(restaurant.getLongitude());

        addressTextView.setText(restaurant.getAddress());
        fragmentManager = this.getSupportFragmentManager();
        restaurantMapFragment = (RestaurantMapFragment) fragmentManager.findFragmentById(R.id.restaurant_map_fragment);
        restaurantMapFragment.setRestaurant(restaurant);
        foodAdapter = new FoodAdapter(this);
        restaurantImageAdapter = new RestaurantImageAdapter(this, restaurant.getImagesUrl());

        restaurantImagesRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        restaurantImagesRecyclerView.stopNestedScroll();

        restaurantImagesRecyclerView.setAdapter(restaurantImageAdapter);

        restaurantMapView = this.restaurantMapFragment.getView();

        nameTextView.setText(restaurant.getName());
        provinceTextView.setText(restaurant.getProvince());
        typeTextView.setText(restaurant.getType());

        wifiNameTextView.setText(!restaurant.getWifi().getName().isEmpty()
                ? restaurant.getWifi().getName()
                : "Thêm wifi");
        wifiPasswordTextView.setText(!restaurant.getWifi().getPassword().isEmpty()
                ? Html.fromHtml("Pass - " + "<b style='color: blue;'>" + restaurant.getWifi().getPassword())
                : "Nhập mật khẩu"
        );

        wifiNameTextView.setOnClickListener(updateWifiTextViewListener);
        restaurant_detail_back_button.setOnClickListener(restaurantDetailBackButtonListener);
        menuLayout.setOnClickListener(menuLayoutClickListener);
        contactButton.setOnClickListener(contactRestaurantViewListener);

        handleTimeOpenCloseView();
        handleDistanceFromUserTextView();
    }

    private View.OnClickListener menuLayoutClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            final Intent goToMenuDetailIntent = new Intent(RestaurantDetailsActivity.this,
                    MenuDetailsActivity.class);
            goToMenuDetailIntent.putExtra(RESTAURANT_ID_EXTRA, restaurant.get_id());
            goToMenuDetailIntent.putExtra(RESTAURANT_NAME__EXTRA, restaurant.getName());
            startActivity(goToMenuDetailIntent);
        }
    };

    private View.OnClickListener restaurantDetailBackButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RestaurantDetailsActivity.super.finish();
        }
    };

    private View.OnClickListener updateWifiTextViewListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            UpdateWifiPasswordDialog updateWifiPasswordDialog = new UpdateWifiPasswordDialog(RestaurantDetailsActivity.this);
            updateWifiPasswordDialog.setRestaurant(restaurant);
            updateWifiPasswordDialog.show();
        }
    };

    private View.OnClickListener contactRestaurantViewListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String url = "tel:" + restaurant.getPhone();
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
            if (ActivityCompat.checkSelfPermission(RestaurantDetailsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            startActivity(intent);
        }
    };

    private void handleDistanceFromUserTextView() {
        currentLocation = ((FoodDeliveryApplication) getApplicationContext()).getUserLocation();
        long distance = (long) currentLocation.distanceTo(restaurantLocation);
        if (distance <= 1000) {
            distanceFromUserTextView.setText(Html.fromHtml(
                    "<b style='color:green important!;'>" + String.format("%d", distance) + "mét </b> " +
                            "<span style='color: black;'>(từ vị trí hiện tại)</span>"
            ));
        } else {
            if ((distance / 1000) > 10) {
                distanceFromUserTextView.setText(Html.fromHtml(
                        "<b style='color: red important!;'>" + String.format("%d", distance / 1000) + "km </b> <span style='color: black;'>(từ vị trí hiện tại)</span>"));
            } else {
                distanceFromUserTextView.setText(Html.fromHtml(
                        "<b style='color: green important!;'>" + String.format("%d", distance / 1000) + "km </b> <span style='color: black;'>(từ vị trí hiện tại)</span>"));
            }
        }
    }

    private void handleTimeOpenCloseView() {

        Calendar currentDateTimeCalendar = Calendar.getInstance();
        currentDateTimeCalendar.setTimeZone(TimeZone.getTimeZone("Asia/Saigon")); // 248 - Asia/Bangkok Timezone ID
        int currentHour = currentDateTimeCalendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = currentDateTimeCalendar.get(Calendar.MINUTE);

        int timeOpenHour, timeOpenMinute, timeCloseHour, timeCloseMinute;

        Calendar tempCalendar = Calendar.getInstance();
        tempCalendar.setTime(restaurant.getTimeOpen());

        timeOpenHour = tempCalendar.get(Calendar.HOUR_OF_DAY);
        timeOpenMinute = tempCalendar.get(Calendar.MINUTE);

        tempCalendar.setTime(restaurant.getTimeClose());
        timeCloseHour = tempCalendar.get(Calendar.HOUR_OF_DAY);
        timeCloseMinute = tempCalendar.get(Calendar.MINUTE);

        int timeOpenInt = (timeOpenHour * 60 + timeOpenMinute);
        int timeCloseInt = (timeCloseHour * 60 + timeOpenMinute);
        int currentTimeInt = currentHour * 60 + currentMinute;
        if (currentTimeInt > timeOpenInt && currentTimeInt < timeCloseInt) {
            openCloseTextView.setText("ĐANG MỞ CỬA");
        } else {
            openCloseTextView.setText("ĐÃ ĐÓNG CỬA");
        }
        openCloseTimeTextView.setText(
                (timeOpenHour >= 10 ? timeOpenHour : "0" + String.valueOf(timeOpenHour))
                        + ":"
                        + (timeOpenMinute >= 10 ? timeOpenMinute : "0" + String.valueOf(timeOpenMinute))
                        + " - "
                        + (timeCloseHour >= 10 ? timeCloseHour : "0" + String.valueOf(timeCloseHour))
                        + ":"
                        + (timeCloseMinute >= 10 ? timeCloseMinute : "0" + String.valueOf(timeCloseMinute)));
    }

    public void handleUpdatedWifi(Wifi wifi) {
        restaurant.setWifi(wifi);
        wifiNameTextView.setText(wifi.getName());
        wifiPasswordTextView.setText(wifi.getPassword());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("RestaurantDetailsActivity Destroyed");
    }

}
