package hcmute.spkt.tranngoctrong.food_delivery.services;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Api {

    private static Api instance;

    private static final String BASE_URL = "https://whispering-citadel-24521.herokuapp.com";

    private GetAsyncTask getAsyncTask;

    private OkHttpClient client;

    static class Method {
        private static final String get = "get";
        private static final String post = "post";
        private static final String put = "put";
        private static final String delete = "delete";
    }

    private Api() {
        client = new OkHttpClient();
    }

    public static Api getInstance() {
        if (instance == null) {
            instance = new Api();
        }
        return instance;
    }
}


