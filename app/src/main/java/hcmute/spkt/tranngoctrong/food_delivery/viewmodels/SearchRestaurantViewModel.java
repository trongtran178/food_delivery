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
    private int pageSize = 10, pageIndex = 1;

    public SearchRestaurantViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        restaurants = new MutableLiveData<List<Restaurant>>();
        restaurantRepository = RestaurantRepository.getInstance();

        // Fetch restaurants from server
        List<Restaurant> restaurants = restaurantRepository.getRestaurants(20, getPageIndex());
        setRestaurants(restaurants);
    }


    public void getNextPage() {
        int currentPageIndex = this.getPageIndex();
        currentPageIndex += 1;
        this.setPageIndex(currentPageIndex);

        List<Restaurant> nextPageRestaurants = restaurantRepository.getRestaurants(20, getPageIndex());

        List<Restaurant> currentPageRestaurants = getRestaurants().getValue();
        if (nextPageRestaurants != null) {
            currentPageRestaurants.addAll(nextPageRestaurants);
            setRestaurants(currentPageRestaurants);
        }
    }

    public MutableLiveData<List<Restaurant>> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants.setValue(restaurants);
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
