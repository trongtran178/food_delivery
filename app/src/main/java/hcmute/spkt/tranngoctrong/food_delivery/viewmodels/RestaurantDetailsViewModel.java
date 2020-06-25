package hcmute.spkt.tranngoctrong.food_delivery.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import hcmute.spkt.tranngoctrong.food_delivery.model.FoodCategory;
import hcmute.spkt.tranngoctrong.food_delivery.model.FoodMenu;
import hcmute.spkt.tranngoctrong.food_delivery.model.Restaurant;
import hcmute.spkt.tranngoctrong.food_delivery.repositories.FoodRepository;
import hcmute.spkt.tranngoctrong.food_delivery.repositories.RestaurantRepository;

public class RestaurantDetailsViewModel extends AndroidViewModel {

    private Restaurant restaurant;
    private MutableLiveData<List<FoodMenu>> foodsInMenu;
    private MutableLiveData<List<FoodCategory>> foodCategories;
    private FoodRepository foodRepository;
    private RestaurantRepository restaurantRepository;
    private boolean isLoading;
    public RestaurantDetailsViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(Restaurant restaurant) {
        this.restaurant = restaurant;
        foodRepository = FoodRepository.getInstance();
        foodCategories = new MutableLiveData<>();
        getFoodCategoriesByRestaurant(restaurant.get_id());
    }

    public void getFoodCategoriesByRestaurant(String restaurantId) {
        List<FoodCategory> fc = new ArrayList<FoodCategory>();
        System.out.println(restaurantId);
        fc = foodRepository.getFoodCategory(restaurantId);
        setFoodCategories(fc);
    }

    public void updateWifi(String password) {
        restaurantRepository = RestaurantRepository.getInstance();
        restaurantRepository.updateWifi(password);
    }


    public void setFoodCategories(List<FoodCategory> foodCategories) {
        this.foodCategories.setValue(foodCategories);
    }

    public MutableLiveData<List<FoodCategory>> getFoodCategories() {
        return foodCategories;
    }


}
