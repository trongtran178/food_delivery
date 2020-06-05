package hcmute.spkt.tranngoctrong.food_delivery.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import hcmute.spkt.tranngoctrong.food_delivery.model.Restaurant;
import hcmute.spkt.tranngoctrong.food_delivery.repositories.RestaurantRepository;

public class SearchRestaurantViewModel extends AndroidViewModel {

    private MutableLiveData<List<Restaurant>> restaurants;
    private RestaurantRepository restaurantRepository;

    public SearchRestaurantViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        restaurants = new MutableLiveData<List<Restaurant>>();
        restaurantRepository = RestaurantRepository.getInstance();

        // Fetch restaurants from server
        List<Restaurant> restaurants = restaurantRepository.getRestaurants();
        setRestaurants(restaurants);

    }

    public MutableLiveData<List<Restaurant>> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants.setValue(restaurants);
    }
}
