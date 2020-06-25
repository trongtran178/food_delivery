package hcmute.spkt.tranngoctrong.food_delivery.services;

import android.os.AsyncTask;

import com.google.gson.JsonObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PutAsyncTask extends AsyncTask<String, Void, Object> {
    private OkHttpClient client;
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public PutAsyncTask(OkHttpClient client) {
        this.client = client;
    }

    // resource: params[0]
    // body: params[1]
    @Override
    protected Object doInBackground(String... params) {
        RequestBody body = RequestBody.Companion.create(params[1], JSON);
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder
                .url(params[0])
                .put(body);
        Request request = requestBuilder.build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
        } finally {
            if (response != null)
                response.close();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
    }


}
