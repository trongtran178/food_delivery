package hcmute.spkt.tranngoctrong.food_delivery.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import hcmute.spkt.tranngoctrong.food_delivery.model.Restaurant;
import hcmute.spkt.tranngoctrong.food_delivery.model.api.Response;
import hcmute.spkt.tranngoctrong.food_delivery.services.Api;

public class RestaurantRepository {

    private static RestaurantRepository instance;
    private Api api;

    public static RestaurantRepository getInstance() {
        if (instance == null) {
            instance = new RestaurantRepository();
        }
        return instance;
    }

    public List<Restaurant> getRestaurants() {
        api = Api.getInstance();
        ObjectMapper mapper = new ObjectMapper();
        List<Restaurant> results;
        try {
            Response response = api.get("/restaurants");
            results = mapper.readValue(mapper.writeValueAsString(response.getResults()), new TypeReference<List<Restaurant>>() {
            });
            return results;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
