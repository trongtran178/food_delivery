package hcmute.spkt.tranngoctrong.food_delivery.fragment;

import android.content.Context;


import androidx.annotation.Nullable;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import hcmute.spkt.tranngoctrong.food_delivery.model.Restaurant;

public class RestaurantMapFragment extends SupportMapFragment implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private Restaurant restaurant;
    private Context context;

    public RestaurantMapFragment() {
        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap gMap) {
        this.googleMap = gMap;
        LatLng restaurantLatLong = new LatLng(restaurant.getLatitude(), restaurant.getLongitude());
        this.googleMap.addMarker(new MarkerOptions().position(restaurantLatLong).title(restaurant.getName()));

        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(restaurantLatLong));
        this.googleMap.setMinZoomPreference(15.0f);
        this.googleMap.setMaxZoomPreference(30.0f);
        this.googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                // Clear previously click position.
                googleMap.clear();
                // Zoom the Marker
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
                // Add Marker on Map
                googleMap.addMarker(markerOptions);
            }
        });
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Nullable
    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
