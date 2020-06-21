package hcmute.spkt.tranngoctrong.food_delivery.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

import hcmute.spkt.tranngoctrong.food_delivery.model.Food;

public class FoodDeserializer extends JsonDeserializer<Food> {
    @Override
    public Food deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode foodNode = p.getCodec().readTree(p);
        Food food = new Food();
        food.set_id(foodNode.get("_id").asText());
        food.setImage(foodNode.get("image").asText());
        food.setName(foodNode.get("name").asText());
        food.setSlug(foodNode.get("slug").asText());
        food.setPrice(foodNode.get("price").doubleValue());
        return food;
    }
}
