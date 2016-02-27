package io.wonderkid.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.wonderkid.midhunchatbot.R;
import io.wonderkid.model.MessageWrapper;

/**
 * Created by midhun on 27/10/15.
 */

public class ChatViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    final int TYPE_CHAT_LEFT = 0;
    final int TYPE_CHAT_RIGHT = 1;

    private List<MessageWrapper> mMessages;

    public ChatViewAdapter(List<MessageWrapper> m) {
        this.mMessages = m;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case TYPE_CHAT_LEFT:
                ViewGroup vGroup = (ViewGroup) mInflater.inflate(R.layout.item_chat_text_left, parent, false);
                ViewHolderChatLeft chatleft = new ViewHolderChatLeft(vGroup);
                return chatleft;

            case TYPE_CHAT_RIGHT:
                ViewGroup vGroup1 = (ViewGroup) mInflater.inflate(R.layout.item_chat_text_right, parent, false);
                ViewHolderChatRight chatright = new ViewHolderChatRight(vGroup1);
                return chatright;

            default:
//                ViewGroup vGroup2 = (ViewGroup) mInflater.inflate(R.layout.item_chat_text_left, parent, false);
//                ViewHolderLeft left2 = new ViewHolderLeft(vGroup2);
                return null;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        MessageWrapper message = mMessages.get(position);
        switch (viewHolder.getItemViewType()) {

            case TYPE_CHAT_LEFT:
                ViewHolderChatLeft chatleft = (ViewHolderChatLeft) viewHolder;
                chatleft.message.setText(message.getMessage().getMessage());
                break;

            case TYPE_CHAT_RIGHT:
                ViewHolderChatRight chatright = (ViewHolderChatRight) viewHolder;
                chatright.message.setText(message.getMessage().getMessage());
                break;

            default:
//                ViewHolderRight right = (ViewHolderRight) viewHolder;
//                right.message.setText(message.getMessage());
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mMessages.get(position).getIsMine())
             return TYPE_CHAT_RIGHT;
        else
            return TYPE_CHAT_LEFT;
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    public void addNewMessage(MessageWrapper msg) {
        mMessages.add(msg);

    }

    public MessageWrapper getMessage(int position){
        return mMessages.get(position);
    }

    public static class ViewHolderChatLeft extends RecyclerView.ViewHolder{

        TextView message;

        public ViewHolderChatLeft(View itemView) {

            super(itemView);

            this.message = (TextView) itemView
                    .findViewById(R.id.message_text);
        }

    }

    public static class ViewHolderChatRight extends RecyclerView.ViewHolder {

        TextView message;

        public ViewHolderChatRight(View itemView) {
            super(itemView);

            this.message = (TextView) itemView
                    .findViewById(R.id.message_text);

        }

    }
}
