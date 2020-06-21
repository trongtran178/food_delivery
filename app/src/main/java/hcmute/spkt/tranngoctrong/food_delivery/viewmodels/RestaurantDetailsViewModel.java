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

public class RestaurantDetailsViewModel extends AndroidViewModel {

    private Restaurant restaurant;
    private MutableLiveData<List<FoodMenu>> foodsInMenu;
    private MutableLiveData<List<FoodCategory>> foodCategories;
    private FoodRepository foodRepository;

    public RestaurantDetailsViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        foodsInMenu = new MutableLiveData<List<FoodMenu>>();
        foodRepository = FoodRepository.getInstance();
    }

    public void getFoodCategoriesByRestaurant(String restaurantId) {
        List<FoodCategory> fc = new ArrayList<FoodCategory>();
        fc = foodRepository.getFoodCategory(restaurantId);
        foodCategories.setValue(fc);
    }

    public MutableLiveData<List<FoodMenu>> getFoodsInMenu() {
        return foodsInMenu;
    }

    public MutableLiveData<List<FoodCategory>> getFoodCategories() {
        return foodCategories;
    }

    public void setFoodsInMenu(List<FoodMenu> foodsWithMenu) {
        this.foodsInMenu.setValue(foodsWithMenu);
    }

    public void setFoodCategories(List<FoodCategory> foodCategories) {
        this.foodCategories.setValue(foodCategories);
    }

}
