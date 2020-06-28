package hcmute.spkt.tranngoctrong.food_delivery.views.search;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hcmute.spkt.tranngoctrong.food_delivery.FoodDeliveryApplication;
import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.adapter.RestaurantAdapter;
import hcmute.spkt.tranngoctrong.food_delivery.model.Restaurant;
import hcmute.spkt.tranngoctrong.food_delivery.utils.OnFoodDeliveryApplicationLoading;
import hcmute.spkt.tranngoctrong.food_delivery.utils.OnLoadMoreListener;
import hcmute.spkt.tranngoctrong.food_delivery.viewmodels.SearchRestaurantViewModel;

public class SearchRestaurantActivity extends AppCompatActivity implements LocationListener,
        OnFoodDeliveryApplicationLoading {

    private FoodDeliveryApplication foodDeliveryApplication;
    private RestaurantAdapter restaurantAdapter;
    private SearchView searchTextInput;
    private SearchRestaurantViewModel searchRestaurantViewModel;
    private RecyclerView restaurantRecyclerView;
    private LinearLayout searchRestaurantLoadingLayout;
    private Handler handler;
    private Location userLocation;
    private LocationManager locationManager;
    private Button chooseProvinceButton;
    private StringBuilder provinceSearch;
    private StringBuilder provinceSlugSearch;
    private boolean hasReceivedLocation = false;

    private static final String SEARCH_QUERY_EXTRA = "SEARCH_QUERY_EXTRA";
    private static final String SEARCH_PROVINCE_EXTRA = "SEARCH_PROVINCE_EXTRA";
    public static final String SEARCH_PROVINCE_SLUG_EXTRA = "SEARCH_PROVINCE_SLUG_EXTRA";

    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_restaurant);

        ActivityCompat.requestPermissions(SearchRestaurantActivity.this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.CALL_PHONE
                }, REQUEST_CODE);


        foodDeliveryApplication = (FoodDeliveryApplication) getApplicationContext();

        chooseProvinceButton = findViewById(R.id.open_choose_province_button);
        searchTextInput = findViewById(R.id.search_restaurant_view);
        searchRestaurantLoadingLayout = findViewById(R.id.search_restaurant_loading_layout);
        handler = new Handler();

        restaurantRecyclerView = findViewById(R.id.restaurant_recycler_view);
        restaurantRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        restaurantRecyclerView.setHasFixedSize(true);

        restaurantAdapter = new RestaurantAdapter(this, restaurantRecyclerView);

        restaurantRecyclerView.setAdapter(restaurantAdapter);


        searchRestaurantViewModel = ViewModelProviders.of(SearchRestaurantActivity.this)
                .get(SearchRestaurantViewModel.class);
        searchRestaurantViewModel.init();

        searchRestaurantViewModel.getRestaurants().observe(SearchRestaurantActivity.this, new Observer<List<Restaurant>>() {
            @Override
            public void onChanged(List<Restaurant> restaurants) {
                restaurantAdapter.setResults(restaurants);
                handleLoading(false);
            }
        });

        restaurantAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                // add null , so the adapter will check view_type and show progress bar at bottom
                List<Restaurant> restaurants = searchRestaurantViewModel.getRestaurants().getValue();
                restaurants.add(null);
                restaurants.add(null);
                restaurants.add(null);
                restaurants.add(null);
                searchRestaurantViewModel.setRestaurants(restaurants);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //   remove progress item
                        final List<Restaurant> restaurants = searchRestaurantViewModel.getRestaurants().getValue();
                        restaurants.remove(restaurants.size() - 1);
                        restaurants.remove(restaurants.size() - 1);
                        restaurants.remove(restaurants.size() - 1);
                        restaurants.remove(restaurants.size() - 1);

                        // load restaurants using api
                        searchRestaurantViewModel.getNextPage();
                        searchRestaurantViewModel.setRestaurants(restaurants);
                        restaurantAdapter.setLoaded();

                    }
                }, 3000);
                restaurantAdapter.notifyDataSetChanged();
            }
        });

        searchTextInput.setOnQueryTextListener(searchViewQueryTextListener);
        chooseProvinceButton.setOnClickListener(openChooseProvince);
        provinceSearch = new StringBuilder(chooseProvinceButton.getText());
        provinceSlugSearch = new StringBuilder("ho-chi-minh");
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (searchRestaurantLoadingLayout.getVisibility() == View.VISIBLE) {
            handleLoading(false);
        }
        System.out.println("Resumedddd");
        handleLocation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("Paused!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println(" stopped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("SearchActivity Destroyed");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                handleLocation();
            } else {
                Toast.makeText(SearchRestaurantActivity.this,
                        "Permission denied to access your location", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                final String province = data.getStringExtra(ChooseProvincesActivity.EXTRA_PROVINCE_SELECTED);
                provinceSlugSearch = new StringBuilder(data.getStringExtra(ChooseProvincesActivity.EXTRA_PROVINCE_SLUG_SELECTED));
                provinceSearch = new StringBuilder(province);
                chooseProvinceButton.setText(province);
            } else {
                // DO NOTHING
            }
        }
    }

    private View.OnClickListener openChooseProvince = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Intent intent = new Intent(SearchRestaurantActivity.this, ChooseProvincesActivity.class);
            intent.putExtra(SEARCH_PROVINCE_SLUG_EXTRA, provinceSlugSearch != null ? provinceSlugSearch.toString() : null);
            startActivityForResult(intent, REQUEST_CODE);
        }
    };

    private SearchView.OnQueryTextListener searchViewQueryTextListener = new SearchView.OnQueryTextListener() {

        @Override
        public boolean onQueryTextSubmit(String query) {
            if (!hasReceivedLocation) return false;
            handleLoading(true);
            Intent searchResultIntent = new Intent(getApplicationContext(), SearchRestaurantResultsActivity.class);
            searchResultIntent.putExtra(SEARCH_QUERY_EXTRA, query);
            searchResultIntent.putExtra(SEARCH_PROVINCE_EXTRA, provinceSearch.toString());
            searchResultIntent.putExtra(SEARCH_PROVINCE_SLUG_EXTRA, provinceSlugSearch.toString());
            startActivity(searchResultIntent);

            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    private void handleLocation() {
        locationManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        userLocation = location;
        foodDeliveryApplication.setUserLocation(location);
        hasReceivedLocation = true;
        System.out.println(217 + ", da lay duoc location");
        // Stop Location Listener
        // https://stackoverflow.com/questions/6894234/stop-location-listener-in-android
        locationManager.removeUpdates(this);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // DO NOTHING
        System.out.println(226 + ", location status change");

    }

    @Override
    public void onProviderEnabled(String provider) {
        System.out.println(232 + ", onProviderEnabled");

    }

    @Override
    public void onProviderDisabled(String provider) {
        System.out.println(238 + ", onProviderDisabled");

    }

    @Override
    public void onHandleLoading(boolean loading) {
        handleLoading(loading);
    }

    public void handleLoading(boolean isLoading) {
        if (isLoading) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    searchRestaurantLoadingLayout.setVisibility(View.VISIBLE);
                }
            });
            return;
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                searchRestaurantLoadingLayout.setVisibility(View.GONE);
                restaurantRecyclerView.setVisibility(View.VISIBLE);
            }
        });
    }
}
