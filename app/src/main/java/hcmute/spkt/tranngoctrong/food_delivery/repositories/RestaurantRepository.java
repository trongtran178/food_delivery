package hcmute.spkt.tranngoctrong.food_delivery.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hcmute.spkt.tranngoctrong.food_delivery.FoodDeliveryApplication;
import hcmute.spkt.tranngoctrong.food_delivery.model.Restaurant;
import hcmute.spkt.tranngoctrong.food_delivery.model.Wifi;
import hcmute.spkt.tranngoctrong.food_delivery.model.api.Response;
import hcmute.spkt.tranngoctrong.food_delivery.model.deserializer.DateDeserializer;
import hcmute.spkt.tranngoctrong.food_delivery.model.deserializer.WifiDeserializer;
import hcmute.spkt.tranngoctrong.food_delivery.services.Api;
import hcmute.spkt.tranngoctrong.food_delivery.views.search.search_results_fragment.FragmentType;

public class RestaurantRepository {

    private static RestaurantRepository instance;
    private Api api;
    private FoodDeliveryApplication foodDeliveryApplication;

    public static RestaurantRepository getInstance() {
        if (instance == null) {
            instance = new RestaurantRepository();

        }
        return instance;
    }

    public List<Restaurant> getRestaurants() {
        api = Api.getInstance();
        ObjectMapper mapper = new ObjectMapper();
        // Deserializable complex json
        mapper.registerModule(new SimpleModule().addDeserializer(Date.class, new DateDeserializer()));
        List<Restaurant> results;
        try {
            Response response = api.get("/restaurants");
            System.out.println(response.toString());
            results = mapper.readValue(mapper.writeValueAsString(response.getResults()), new TypeReference<List<Restaurant>>() {
            });
            System.out.println(results.toString());
            return results;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Restaurant> getRestaurants(int pageSize, int pageIndex) {
        api = Api.getInstance();
        ObjectMapper mapper = new ObjectMapper();
        // Deserializable complex json
        mapper.registerModule(new SimpleModule().addDeserializer(Date.class, new DateDeserializer()));
        mapper.registerModule(new SimpleModule().addDeserializer(Wifi.class, new WifiDeserializer()));

        List<Restaurant> results;
        try {
            Response response = api.get("/restaurants?pageSize=" + pageSize + "&pageIndex=" + pageIndex);
            results = mapper.readValue(mapper.writeValueAsString(response.getResults()), new TypeReference<List<Restaurant>>() {
            });
            System.out.println(results.toString());
            return results;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Restaurant> searchRestaurantsByKeyWord(String keyword, FragmentType fragmentType) {
        api = Api.getInstance();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new SimpleModule().addDeserializer(Date.class, new DateDeserializer()));
        mapper.registerModule(new SimpleModule().addDeserializer(Wifi.class, new WifiDeserializer()));
        List<Restaurant> results;
        try {
            Response response = null;
            switch (fragmentType) {
                case MOST_RIGHT: {
                    response = api.get("/restaurants?keyword=" + keyword);
                    break;
                }
                case NEAR_ME: {
                    response = api.get("/restaurants?keyword=" + keyword
                            + "&latitude=" + foodDeliveryApplication.getUserLocation().getLatitude()
                            + "&longitude=" + foodDeliveryApplication.getUserLocation().getLongitude());
                    break;
                }
            }
            results = mapper.readValue(mapper.writeValueAsString(response.getResults()),
                    new TypeReference<List<Restaurant>>() {
                    });
            return results;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateWifi(String restaurantId, String password) {
        api = Api.getInstance();
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, String> body = new HashMap<>();
            body.put("password", password);
            Response response = api.put("/restaurants/" + restaurantId + "/wifi", body);
            if (response.getCode() == 200) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public void setFoodDeliveryApplication(FoodDeliveryApplication foodDeliveryApplication) {
        this.foodDeliveryApplication = foodDeliveryApplication;
    }
}
