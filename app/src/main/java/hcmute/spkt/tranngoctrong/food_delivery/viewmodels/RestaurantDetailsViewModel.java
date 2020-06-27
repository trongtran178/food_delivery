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

    private MutableLiveData<List<FoodCategory>> foodCategories;
    private FoodRepository foodRepository;
    private RestaurantRepository restaurantRepository;

    public RestaurantDetailsViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String restaurantId) {
        foodRepository = FoodRepository.getInstance();
        foodCategories = new MutableLiveData<>();
//        getFoodCategoriesByRestaurant(restaurantId);
    }

    public void getFoodCategoriesByRestaurant(String restaurantId) {
        List<FoodCategory> fc = new ArrayList<FoodCategory>();
        System.out.println(restaurantId);
        fc = foodRepository.getFoodCategory(restaurantId);
        setFoodCategories(fc);
    }

    public boolean updateWifi(String restaurantId, String password) {
        restaurantRepository = RestaurantRepository.getInstance();
        boolean isUpdated = restaurantRepository.updateWifi(restaurantId, password);
        return isUpdated;
    }

    public void setFoodCategories(List<FoodCategory> foodCategories) {
        this.foodCategories.setValue(foodCategories);
    }

    public MutableLiveData<List<FoodCategory>> getFoodCategories() {
        return foodCategories;
    }


}
