package hcmute.spkt.tranngoctrong.food_delivery.viewmodels;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.text.Normalizer;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import hcmute.spkt.tranngoctrong.food_delivery.FoodDeliveryApplication;
import hcmute.spkt.tranngoctrong.food_delivery.model.Restaurant;
import hcmute.spkt.tranngoctrong.food_delivery.repositories.RestaurantRepository;
import hcmute.spkt.tranngoctrong.food_delivery.views.search.search_results_fragment.FragmentType;

public class SearchRestaurantResultsViewModel extends AndroidViewModel {

    private MutableLiveData<List<Restaurant>> restaurants;
    private RestaurantRepository restaurantRepository;
    private FoodDeliveryApplication foodDeliveryApplication;

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public SearchRestaurantResultsViewModel(@NonNull Application application) {
        super(application);
        foodDeliveryApplication = (FoodDeliveryApplication) getApplication().getApplicationContext();
    }

    public void init() {
        restaurants = new MutableLiveData<List<Restaurant>>();
        restaurantRepository = RestaurantRepository.getInstance();
        restaurantRepository.setFoodDeliveryApplication(foodDeliveryApplication);
    }

    public void searchRestaurantsByKeyword(String keyword, String province, FragmentType fragmentType) {
        // Fetch searched restaurants result from server
        List<Restaurant> restaurants = restaurantRepository.searchRestaurantsByKeyWord(keyword, toSlug(province), fragmentType);
        setRestaurants(restaurants);
    }


    public MutableLiveData<List<Restaurant>> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants.setValue(restaurants);
    }

    private String toSlug(String input) {
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }
}
