package io.wonderkid.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by midhun on 20/2/16.
 */

public class MyRetrofit {

//    static String API_URL = "http://128.199.244.1";
    static String API_URL = "http://4dceb8a2.ngrok.io";
    static RippleService rippleService;

    public MyRetrofit() {
    }

    public static RippleService getInstance() {
        if(rippleService == null)
        {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            rippleService = retrofit.create(RippleService.class);

            return rippleService;
        }
        else
            return rippleService;
    }

}
