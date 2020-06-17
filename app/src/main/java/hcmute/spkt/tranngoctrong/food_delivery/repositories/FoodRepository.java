package hcmute.spkt.tranngoctrong.food_delivery.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.List;

import hcmute.spkt.tranngoctrong.food_delivery.model.Food;
import hcmute.spkt.tranngoctrong.food_delivery.model.FoodMenu;
import hcmute.spkt.tranngoctrong.food_delivery.model.Menu;
import hcmute.spkt.tranngoctrong.food_delivery.model.Restaurant;
import hcmute.spkt.tranngoctrong.food_delivery.model.api.Response;
import hcmute.spkt.tranngoctrong.food_delivery.model.deserializer.FoodMenuDeserializer;
import hcmute.spkt.tranngoctrong.food_delivery.services.Api;

public class FoodRepository {

    private static FoodRepository instance;
    private Api api;

    public static FoodRepository getInstance() {
        if (instance == null) {
            instance = new FoodRepository();
        }
        return instance;
    }

    public List<FoodMenu> getFoodsByMenu(int menuId) {
        api = Api.getInstance();
        ObjectMapper mapper = new ObjectMapper();

        // Deserializable complex json
        mapper.registerModule(new SimpleModule().addDeserializer(FoodMenu.class, new FoodMenuDeserializer()));

        List<FoodMenu> results;
        try {
            Response response = api.get("/food/menu");

            results = mapper.readValue(
                    mapper.writeValueAsString(response.getResults()),
                    new TypeReference<List<FoodMenu>>() {
            });


            System.out.println(results.toString());
            return results;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

