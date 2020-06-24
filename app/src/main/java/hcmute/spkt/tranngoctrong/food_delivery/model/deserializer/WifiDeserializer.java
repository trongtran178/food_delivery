package hcmute.spkt.tranngoctrong.food_delivery.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

import hcmute.spkt.tranngoctrong.food_delivery.model.Wifi;

public class WifiDeserializer extends JsonDeserializer<Wifi> {
    @Override
    public Wifi deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode wifiNode = p.getCodec().readTree(p);
        Wifi wifi = new Wifi();
        wifi.setName(wifiNode.get("name").asText());
        wifi.setPassword(wifiNode.get("password").asText());
        return wifi;
    }
}
