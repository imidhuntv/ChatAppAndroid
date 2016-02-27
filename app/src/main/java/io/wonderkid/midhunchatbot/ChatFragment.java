package io.wonderkid.midhunchatbot;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.wonderkid.model.MessageWrapper;
import io.wonderkid.network.BotService;
import io.wonderkid.network.MyRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatFragment extends Fragment {

    @Bind(R.id.chatList)
    RecyclerView chatList;
    @Bind(R.id.inputMessage)
    EditText inputMessage;
    @Bind(R.id.sendMessage)
    ImageButton sendMessage;

    BotService botService;

    public ChatFragment() {
        // Required empty public constructor
    }


    public static ChatFragment newInstance() {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_chat, container, false);

        ButterKnife.bind(this,v);

        botService = MyRetrofit.getInstance();

        Call<MessageWrapper> call = botService.sendReceiveMessage("6nt5d1nJHkqbkphe",
                "Hi",
                "63906",
                "chirag1"
        );

        call.enqueue(new Callback<MessageWrapper>() {
            @Override
            public void onResponse(Call<MessageWrapper> call, Response<MessageWrapper> response) {
                Log.i("success",response.body().getMessage().getMessage());
            }

            @Override
            public void onFailure(Call<MessageWrapper> call, Throwable t) {
                Log.i("success",t.getMessage());
            }
        });

        return v;
    }

}
