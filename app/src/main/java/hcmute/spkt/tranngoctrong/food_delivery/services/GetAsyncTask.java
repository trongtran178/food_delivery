package hcmute.spkt.tranngoctrong.food_delivery.services;

import android.os.AsyncTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetAsyncTask extends AsyncTask<String, Void, Object> {

    private OkHttpClient client;

    public GetAsyncTask() {
        client = new OkHttpClient();
    }

    @Override
    protected Object doInBackground(String... params) {

        Request.Builder builder = new Request.Builder();
        builder.url(params[0]);
        System.out.println(params[0]);
        Request request = builder.build();
        Response response = null;
        try {
             response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if(response != null)
                response.close();
        }

        return null;
    }
}
