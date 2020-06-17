package hcmute.spkt.tranngoctrong.food_delivery.views.search;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
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

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.adapter.FoodAdapter;
import hcmute.spkt.tranngoctrong.food_delivery.fragment.RestaurantMapFragment;
import hcmute.spkt.tranngoctrong.food_delivery.model.Food;
import hcmute.spkt.tranngoctrong.food_delivery.model.FoodMenu;
import hcmute.spkt.tranngoctrong.food_delivery.model.Restaurant;
import hcmute.spkt.tranngoctrong.food_delivery.viewmodels.RestaurantDetailsViewModel;

public class RestaurantDetailsActivity extends AppCompatActivity implements LocationListener {

    private RecyclerView restaurantFoodsRecyclerView;
    private FoodAdapter foodAdapter;
    private RestaurantMapFragment restaurantMapFragment;
    private Restaurant restaurant;
    private LinearLayout menuLayout;
    private TextView addWifiTextView;
    private ImageButton restaurant_detail_back_button;
    private FragmentManager fragmentManager;
    private RestaurantDetailsViewModel restaurantDetailsViewModel;
    private View restaurantMapView;

    private Location currentLocation, restaurantLocation;

    private TextView addressTextView, distanceFromUserTextView, openCloseTextView, openCloseTimeTextView;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
        openCloseTextView = findViewById(R.id.restaurant_open_close_text_view);
        openCloseTimeTextView = findViewById(R.id.restaurant_open_close_time_text_view);
        addressTextView = findViewById(R.id.restaurant_detail_address_text_view);
        distanceFromUserTextView = findViewById(R.id.restaurant_detail_distance_from_user_text_view);
        addWifiTextView = findViewById(R.id.add_wifi_text_view);
        restaurant_detail_back_button = findViewById(R.id.restaurant_detail_back_button);
        restaurantFoodsRecyclerView = findViewById(R.id.restaurant_foods_recycler_view);
        menuLayout = findViewById(R.id.menu_layout);

        restaurant = getIntent().getParcelableExtra("restaurant");
        handleOpenCloseView();
        restaurantLocation = new Location("restaurantLocation");
        restaurantLocation.setLatitude(restaurant.getLatitude());
        restaurantLocation.setLongitude(restaurant.getLongitude());

        addressTextView.setText(restaurant.getAddress());
        fragmentManager = this.getSupportFragmentManager();
        restaurantMapFragment = (RestaurantMapFragment) fragmentManager.findFragmentById(R.id.restaurant_map_fragment);

        foodAdapter = new FoodAdapter(this);
        restaurantFoodsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        restaurantFoodsRecyclerView.stopNestedScroll();

        restaurantDetailsViewModel = ViewModelProviders.of(this).get(RestaurantDetailsViewModel.class);
        restaurantDetailsViewModel.init();
        restaurantDetailsViewModel.getFoodsInMenu().observe(this, new Observer<List<FoodMenu>>() {
            @Override
            public void onChanged(List<FoodMenu> foodMenus) {
                foodAdapter.setRestaurantFoodsByMenu(foodMenus);
                restaurantFoodsRecyclerView.setAdapter(foodAdapter);
            }
        });

        restaurantMapView = this.restaurantMapFragment.getView();
        restaurantMapView.setAlpha(0.25f);

        addWifiTextView.setOnClickListener(addWifiTextViewListener);
        restaurant_detail_back_button.setOnClickListener(restaurantDetailBackButtonListener);
        menuLayout.setOnClickListener(menuLayoutClickListener);
        handleLocation();
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

    private void handleOpenCloseView() {

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
            openCloseTextView.setText("Đang mở cửa");
        } else {
            openCloseTextView.setText("Đã đóng cửa");
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

    private void handleLocation() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("RestaurantDetailsActivity Destroyed");
    }


    @Override
    public void onLocationChanged(Location location) {
        currentLocation = location;
        distanceFromUserTextView.setText(distanceFromUserString());
        System.out.println(currentLocation);
    }

    private String distanceFromUserString() {
        long distance = (long) currentLocation.distanceTo(restaurantLocation);
        if(distance < 1000) {
            return String.format("%d", distance) + "mét (từ vị trí hiện tại)";
        } else {
            return String.format("%d", distance / 1000) + " km (từ vị trí hiện tại)";
        }
    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude", "status");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude", "enable");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude", "disable");

    }
}
