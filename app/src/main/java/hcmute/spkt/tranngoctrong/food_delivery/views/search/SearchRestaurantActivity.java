package hcmute.spkt.tranngoctrong.food_delivery.views.search;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hcmute.spkt.tranngoctrong.food_delivery.R;
import hcmute.spkt.tranngoctrong.food_delivery.adapter.RestaurantAdapter;
import hcmute.spkt.tranngoctrong.food_delivery.model.Restaurant;
import hcmute.spkt.tranngoctrong.food_delivery.viewmodels.SearchRestaurantViewModel;

public class SearchRestaurantActivity extends AppCompatActivity {

    private RestaurantAdapter restaurantAdapter;
    private Button chooseProvinceButton;
//    private ProgressBar searchRestaurantProgressBar;
    private SearchView searchTextInput;
    private SearchRestaurantViewModel searchRestaurantViewModel;
    private RecyclerView restaurantRecyclerView;

    private static final String SEARCH_QUERY_EXTRA = "SEARCH_QUERY_EXTRA";
    private static final int REQUEST_CODE = 1;
    private static final int PERMISSIONS_REQUEST_LOCATION_CODE = 2;
    protected LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_restaurant_page);
//        searchRestaurantProgressBar = findViewById(R.id.search_restaurant_progress_bar);
        chooseProvinceButton = findViewById(R.id.open_choose_province_button);
        searchTextInput = findViewById(R.id.search_restaurant_view);

        restaurantRecyclerView = findViewById(R.id.restaurant_recycler_view);

        restaurantAdapter = new RestaurantAdapter(this);
        restaurantRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        restaurantRecyclerView.setHasFixedSize(true);

        searchRestaurantViewModel = ViewModelProviders.of(this).get(SearchRestaurantViewModel.class);
        searchRestaurantViewModel.init();
        restaurantRecyclerView.setAdapter(restaurantAdapter);

        searchTextInput.setOnQueryTextListener(searchViewQueryTextListener);
        chooseProvinceButton.setOnClickListener(openChooseProvince);

        searchRestaurantViewModel.getRestaurants().observe(this, new Observer<List<Restaurant>>() {
            @Override
            public void onChanged(List<Restaurant> restaurants) {
                restaurantAdapter.setResults(restaurants);
            }
        });

        ActivityCompat.requestPermissions(SearchRestaurantActivity.this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.CALL_PHONE},
                REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                Toast.makeText(SearchRestaurantActivity.this, "Permission denied to access your location", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
//        searchRestaurantProgressBar.setVisibility(View.VISIBLE);
    }

    private View.OnClickListener openChooseProvince = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Intent intent = new Intent(SearchRestaurantActivity.this, ChooseProvincesActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        }
    };

    private SearchView.OnQueryTextListener searchViewQueryTextListener = new SearchView.OnQueryTextListener() {

        @Override
        public boolean onQueryTextSubmit(String query) {
            System.out.println(query);
            Intent searchResultIntent = new Intent(getApplicationContext(), SearchRestaurantResultsActivity.class);
            searchResultIntent.putExtra(SEARCH_QUERY_EXTRA, query);
            startActivity(searchResultIntent);

            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                final String province = data.getStringExtra(ChooseProvincesActivity.EXTRA_PROVINCE_SELECTED);
                chooseProvinceButton.setText(province);
                System.out.println(province);
            } else {
                // DO NOTHING
            }
        }
    }

    private boolean checkPermissionLocation() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle(R.string.app_name)
                        .setMessage(R.string.app_name)
                        .setPositiveButton(R.string.app_name, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(SearchRestaurantActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        PERMISSIONS_REQUEST_LOCATION_CODE);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSIONS_REQUEST_LOCATION_CODE);
            }
            return false;
        } else {
            return true;
        }
    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case PERMISSIONS_REQUEST_LOCATION_CODE: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    // permission was granted, yay! Do the
//                    // location-related task you need to do.
//                    if (ContextCompat.checkSelfPermission(this,
//                            Manifest.permission.ACCESS_FINE_LOCATION)
//                            == PackageManager.PERMISSION_GRANTED) {
//
//                        //Request location updates:
//                        locationManager.requestLocationUpdates(provider, 400, 1, this);
//                    }
//
//                } else {
//
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//
//                }
//                return;
//            }
//
//        }
//    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("Resumedddd");
//        if (searchRestaurantViewModel.getRestaurants().getValue().size() <= 0 || searchRestaurantViewModel.getRestaurants().getValue() == null)
//            searchRestaurantProgressBar.setVisibility(View.VISIBLE);
//        else searchRestaurantProgressBar.setVisibility(View.GONE);
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
}
