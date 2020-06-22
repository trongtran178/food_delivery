package hcmute.spkt.tranngoctrong.food_delivery.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

import hcmute.spkt.tranngoctrong.food_delivery.model.api.Pagination;

public class PaginationDeserializer extends JsonDeserializer<Pagination> {
    @Override
    public Pagination deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode paginationNode = p.getCodec().readTree(p);
        Pagination pagination = new Pagination();
        pagination.setCount(paginationNode.get("count").intValue());
        pagination.setPageCount(paginationNode.get("pageSize").intValue());
        pagination.setPageIndex(paginationNode.get("pageIndex").intValue());
        pagination.setPageCount(paginationNode.get("pageCount").intValue());
        return pagination;

    }
}
