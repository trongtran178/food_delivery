package hcmute.spkt.tranngoctrong.food_delivery.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import hcmute.spkt.tranngoctrong.food_delivery.model.FoodMenu;
import hcmute.spkt.tranngoctrong.food_delivery.model.Restaurant;
import hcmute.spkt.tranngoctrong.food_delivery.repositories.FoodRepository;

public class RestaurantDetailsViewModel extends AndroidViewModel {

    private Restaurant restaurant;
    private MutableLiveData<List<FoodMenu>> foodsInMenu;
    private FoodRepository foodRepository;

    public RestaurantDetailsViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        foodsInMenu = new MutableLiveData<List<FoodMenu>>();
        foodRepository = FoodRepository.getInstance();

        // Fetch foods of menu result from server
        List<FoodMenu> foodsWithMenu = foodRepository.getFoodsByMenu(1);
        setFoodsInMenu(foodsWithMenu);
    }

    public MutableLiveData<List<FoodMenu>> getFoodsInMenu() {
        return foodsInMenu;
    }

    public void setFoodsInMenu(List<FoodMenu> foodsWithMenu) {
        this.foodsInMenu.setValue(foodsWithMenu);
    }

}
