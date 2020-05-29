package hcmute.spkt.tranngoctrong.food_delivery.model.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Response {

    private int code;
    private String status;
    private boolean isSuccess;
    private Object results;

    public Response() {}

    public Response(int code, String status, boolean isSuccess, Object results) {
        super();
        this.code = code;
        this.status = status;
        this.isSuccess = isSuccess;
        this.results = results;
    }

    public String toJson(Response response) {
        return null;
    }

    public Response fromJson(String responseString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        Response response = mapper.readValue(responseString, Response.class);

        return response;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public Object getResults() {
        return results;
    }

    public void setResults(Object results) {
        this.results = results;
    }
}
