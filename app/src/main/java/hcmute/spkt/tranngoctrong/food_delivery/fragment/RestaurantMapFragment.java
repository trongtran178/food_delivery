package hcmute.spkt.tranngoctrong.food_delivery.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class RestaurantMapFragment extends SupportMapFragment implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private LatLng deviceLatLng;
    private LatLng restaurantLatLng;
    private LocationManager locationManager;


    public RestaurantMapFragment() {
//        this.restaurantLatLng = RestaurantMapFragment.this.get;
        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap gMap) {
//        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
//        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//        restaurantLatLng = new LatLng(10.843972, 106.767511);
//        deviceLatLng = new LatLng(location.getLatitude(), location.getLongitude());

        this.googleMap = gMap;

//        LatLng vietnam = new LatLng(14.0583, 108.2772); // 14.0583° N, 108.2772° E
        LatLng vietnam = new LatLng(10.843972, 106.767511);
        this.googleMap.addMarker(new MarkerOptions().position(vietnam).title("Bun dau co Chang"));
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(vietnam));
        this.googleMap.setMinZoomPreference(20.0f);
        this.googleMap.setMaxZoomPreference(50.0f);
        this.googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                // Clear previously click position.
                googleMap.clear();
                // Zoom the Marker
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                // Add Marker on Map
                googleMap.addMarker(markerOptions);
            }
        });
    }
}
