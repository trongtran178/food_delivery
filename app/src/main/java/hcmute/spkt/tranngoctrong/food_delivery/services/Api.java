package hcmute.spkt.tranngoctrong.food_delivery.services;

import java.util.Map;

import hcmute.spkt.tranngoctrong.food_delivery.model.api.Response;
import okhttp3.OkHttpClient;

public class Api {

    private static Api instance;

    //    private static final String BASE_URL = "https://whispering-citadel-24521.herokuapp.com"; // DEPLOYED
    private static final String BASE_URL = "https://murmuring-plains-40357.herokuapp.com/api"; // DEPLOYED
    //    private static final String BASE_URL = "http://localhost:8080";
//    http://murmuring-plains-40357.herokuapp.com/api/restaurants?keyword=ngang
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
    /// resource: /restaurants
    /// data: {
    //      password: 123456
    // }
    public Response put(String resource, Map<String, String> data) {
        putAsyncTask = new PutAsyncTask(client);
        try {
            String resultsJsonString = (String) putAsyncTask.doInBackground(getUrl(resource),
                    data.toString());

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


