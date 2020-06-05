package hcmute.spkt.tranngoctrong.food_delivery.services;

import hcmute.spkt.tranngoctrong.food_delivery.model.api.Response;
import okhttp3.OkHttpClient;

public class Api {

    private static Api instance;

    private static final String BASE_URL = "https://whispering-citadel-24521.herokuapp.com";

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

    // sample url: /restaurants
    public Response get(String resource) {
        getAsyncTask = new GetAsyncTask(client);
        try {
            String resultsJsonString = (String) getAsyncTask.execute(getUrl(resource)).get();
            Response response = new Response().fromJson(resultsJsonString);
            getAsyncTask.cancel(true);
            return response;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    public Response post(String resource) {
        postAsyncTask = new PostAsyncTask();
        return null;
    }

    public Response put(String resource) {
        putAsyncTask = new PutAsyncTask();
        return null;
    }

    public Response delete(String resource) {
        deleteAsyncTask = new DeleteAsyncTask();
        return null;
    }


    private String getUrl(String resource) {
        return BASE_URL + resource;
    }

}


