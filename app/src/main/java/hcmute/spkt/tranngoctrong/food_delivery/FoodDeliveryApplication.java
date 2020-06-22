package hcmute.spkt.tranngoctrong.food_delivery;

import android.app.Application;
import android.location.Location;

public class FoodDeliveryApplication extends Application {
    private Location userLocation;

    public Location getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(Location userLocation) {
        this.userLocation = userLocation;
    }
}
