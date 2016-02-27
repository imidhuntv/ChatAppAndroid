package io.wonderkid.network;

import io.wonderkid.model.MessageWrapper;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by midhun on 20/2/16.
 */
public interface BotService {

    @GET("api/chat")
    Call<MessageWrapper> sendReceiveMessage(@Query("apiKey") String apiKey,
                                            @Query("message") String message,
                                            @Query("chatBotID") String chatBotID,
                                            @Query("externalID") String externalID);

}
