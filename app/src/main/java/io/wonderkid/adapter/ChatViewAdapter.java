package io.wonderkid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import io.wonderkid.midhunchatbot.R;
import io.wonderkid.model.MessageWrapper;

/**
 * Created by midhun on 27/10/15.
 */

public class ChatViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    final int TYPE_CHAT_LEFT = 0;
    final int TYPE_CHAT_RIGHT = 1;

    private Context mContext;
    private List<MessageWrapper> mMessages;
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");

    public ChatViewAdapter(Context context, List<MessageWrapper> m) {
        this.mContext = context;
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
//                Date resultdate1 = new Date(Long.parseLong(message.getMessageTime()));
//                left.msg_time.setText(sdf.format(resultdate1));
                break;

            case TYPE_CHAT_RIGHT:
                ViewHolderChatRight chatright = (ViewHolderChatRight) viewHolder;
                chatright.message.setText(message.getMessage().getMessage());
//                Date resultdate2 = new Date(Long.parseLong(message.getMessageTime()));
//                right.msg_time.setText(sdf.format(resultdate2));
                break;

            default:
//                ViewHolderRight right = (ViewHolderRight) viewHolder;
//                right.message.setText(message.getMessage());
//                Date resultdate2 = new Date(Long.parseLong(message.getTime()));
//                right.msg_time.setText(sdf.format(resultdate2));
//                right.msg_user.setText("you");
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mMessages.get(position).getMessage().getChatBotID().equals("63906"))
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

        TextView message, msg_time, msg_user;
        ImageView avatar;
        LinearLayout layout;
        RelativeLayout msg_layout;

        public ViewHolderChatLeft(View itemView) {

            super(itemView);
//            this.avatar = (ImageView) itemView
//                    .findViewById(R.id.avatar);

            this.msg_time = (TextView) itemView
                    .findViewById(R.id.msg_time);
            this.message = (TextView) itemView
                    .findViewById(R.id.message_text);
//            itemView.setOnLongClickListener(this);
        }

    }

    public static class ViewHolderChatRight extends RecyclerView.ViewHolder {

        TextView message, msg_time, msg_user;
        ImageView avatar;
        RelativeLayout layout;
        RelativeLayout msg_layout;

        public ViewHolderChatRight(View itemView) {
            super(itemView);

//            this.avatar = (ImageView) itemView
//                    .findViewById(R.id.avatar);

            this.msg_time = (TextView) itemView
                    .findViewById(R.id.msg_time);
            this.message = (TextView) itemView
                    .findViewById(R.id.message_text);

//            itemView.setOnLongClickListener(this);

        }

    }
}
