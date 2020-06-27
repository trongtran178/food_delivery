package hcmute.spkt.tranngoctrong.food_delivery.services;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

import hcmute.spkt.tranngoctrong.food_delivery.model.api.Response;
import okhttp3.OkHttpClient;

public class Api {

    private static Api instance;

    // private static final String BASE_URL = "http://localhost:8080";
    private static final String BASE_URL = "https://murmuring-plains-40357.herokuapp.com/api"; // DEPLOYED

    private GetAsyncTask getAsyncTask;
    private PostAsyncTask postAsyncTask;
    private PutAsyncTask putAsyncTask;
    private DeleteAsyncTask deleteAsyncTask;

    private OkHttpClient client;

    private Api() {
        client = new OkHttpClient();
    }

    public static Api getInstance() {
        if (instance == null) {
            instance = new Api();
        }
        return instance;
    }

    /// sample:
    /// resource: /restaurants
    public Response get(String resource) {
        getAsyncTask = new GetAsyncTask(client);
        try {
            String resultsJsonString = (String) getAsyncTask.execute(getUrl(resource)).get();
            System.out.println(resultsJsonString);
            Response response = new Response().fromJson(resultsJsonString);
            getAsyncTask.cancel(true);
            return response;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    // Unused
    public Response post(String resource) {
        postAsyncTask = new PostAsyncTask();
        return null;
    }

    /// sample:
    /// resource: /restaurants/:restaurantId/edit
    /// data: {
    //      password: 123456
    // }
    public Response put(String resource, Map<String, String> data) {
        putAsyncTask = new PutAsyncTask(client);
        try {
            System.out.println(data.toString());
            ObjectMapper objectMapper = new ObjectMapper();
            String dataJsonString = objectMapper.writeValueAsString(data);
            String resultsJsonString = (String) putAsyncTask.execute(getUrl(resource), dataJsonString).get();
            System.out.println(resultsJsonString);
            Response response = new Response().fromJson(resultsJsonString);

            putAsyncTask.cancel(true);

            return response;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    // Unused
    public Response delete(String resource) {
        deleteAsyncTask = new DeleteAsyncTask();
        return null;
    }

    private String getUrl(String resource) {
        return BASE_URL + resource;
    }
}


