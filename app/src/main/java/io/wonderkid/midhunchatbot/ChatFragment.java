package io.wonderkid.midhunchatbot;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.wonderkid.adapter.ChatViewAdapter;
import io.wonderkid.model.Message;
import io.wonderkid.model.MessageWrapper;
import io.wonderkid.network.BotService;
import io.wonderkid.network.MyRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatFragment extends Fragment{

    @Bind(R.id.chatList)
    RecyclerView chatList;
    @Bind(R.id.inputMessage)
    EditText inputMessage;
    @Bind(R.id.sendMessage)
    ImageButton sendMessage;

    BotService botService;
    ChatViewAdapter mAdapter;

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_chat, container, false);

        ButterKnife.bind(this, v);
        init();

        return v;
    }

    private void init(){
        botService = MyRetrofit.getInstance();
        mAdapter = new ChatViewAdapter(getActivity(),new ArrayList<MessageWrapper>());

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        chatList.setLayoutManager(llm);
        chatList.setItemAnimator(new DefaultItemAnimator());
        chatList.setAdapter(mAdapter);
    }

    @OnClick(R.id.sendMessage)
    public void sendOrReceiveMessage(ImageButton button){

        String messageString = inputMessage.getText().toString();

        Message message = new Message();
        message.setChatBotID(1234);
        message.setChatBotName("Midhun");
        message.setEmotion("");

        MessageWrapper messageWrapper = new MessageWrapper();
        messageWrapper.setMessage(message);

        mAdapter.addNewMessage(messageWrapper);
        mAdapter.notifyDataSetChanged();

        Call<MessageWrapper> call = botService.sendReceiveMessage("6nt5d1nJHkqbkphe",
                messageString,
                "63906",
                "chirag1"
        );

        call.enqueue(new Callback<MessageWrapper>() {
            @Override
            public void onResponse(Call<MessageWrapper> call, Response<MessageWrapper> response) {
                Log.i("success",response.body().getMessage().getMessage());
                mAdapter.addNewMessage(response.body());
            }

            @Override
            public void onFailure(Call<MessageWrapper> call, Throwable t) {
                Log.i("success",t.getMessage());
            }
        });
    }

}
