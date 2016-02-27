package io.wonderkid.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by midhun on 20/2/16.
 */

public class MyRetrofit {

    static String API_URL = "http://www.personalityforge.com/";
    static BotService botService;

    public MyRetrofit() {
    }

    public static BotService getInstance() {
        if(botService == null)
        {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            botService = retrofit.create(BotService.class);

            return botService;
        }
        else
            return botService;
    }

}
