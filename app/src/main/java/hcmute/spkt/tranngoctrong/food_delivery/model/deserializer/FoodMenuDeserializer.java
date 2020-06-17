package hcmute.spkt.tranngoctrong.food_delivery.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

import hcmute.spkt.tranngoctrong.food_delivery.model.Food;
import hcmute.spkt.tranngoctrong.food_delivery.model.FoodMenu;
import hcmute.spkt.tranngoctrong.food_delivery.model.Menu;

public class FoodMenuDeserializer extends JsonDeserializer<FoodMenu> {

    @Override
    public FoodMenu deserialize(JsonParser p, DeserializationContext context) throws IOException {
        JsonNode foodMenuNode = p.getCodec().readTree(p);
        FoodMenu foodMenu = new FoodMenu();
        foodMenu.setId(foodMenuNode.get("id").intValue());
        foodMenu.setPrice(foodMenuNode.get("price").doubleValue());
        foodMenu.setFood(new Food(foodMenuNode.get("food").get("id").intValue(),
                foodMenuNode.get("food").get("name").asText(),
                foodMenuNode.get("food").get("slug").asText()
        ));
        foodMenu.setMenu(new Menu(
                foodMenuNode.get("menu").get("id").intValue(),
                foodMenuNode.get("menu").get("name").asText(),
                foodMenuNode.get("menu").get("slug").asText(),
                foodMenuNode.get("menu").get("query").asText()
        ));
        foodMenu.setFoodAvatarUrl(foodMenuNode.get("foodAvatarUrl").asText());
        return foodMenu;
    }
}
