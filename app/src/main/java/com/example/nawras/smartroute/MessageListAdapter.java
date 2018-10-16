package com.example.nawras.smartroute;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nawras.smartroute.Beans.Message;
import com.example.nawras.smartroute.Beans.Tools;
import com.github.mikephil.charting.utils.Utils;

import java.util.List;

/**
 * Created by Hamza on 15/04/18.
 */

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MyViewHolder> {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    private Context mContext;
    private List<Message> mMessageList;
    private int currentUserId;

    public MessageListAdapter(Context context, List<Message> messageList,int id) {
        mContext = context;
        mMessageList = messageList;
        currentUserId = id;
    }

    @Override
    public int getItemCount() {
        //System.out.println("Got Count : "+mMessageList.size());
        return mMessageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Message message = (Message) mMessageList.get(position);
        if (message.getIdUtilEnvoyant()==currentUserId) {
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    @Override
    public MessageListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //System.out.print("View Type : "+viewType);
        if (viewType == VIEW_TYPE_MESSAGE_SENT) {

            return new SentMessageHolder(LayoutInflater.from(mContext).inflate(R.layout.item_message_sent, parent, false));
        }
        else
        {
            return new ReceivedMessageHolder(LayoutInflater.from(mContext).inflate(R.layout.item_message_received, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(MessageListAdapter.MyViewHolder holder, int position) {
        Message message = (Message) mMessageList.get(position);
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(message);
                break;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
    private class SentMessageHolder extends MyViewHolder {
        TextView messageText, timeText;
        SentMessageHolder(View itemView) {
            super(itemView);
            messageText = (TextView) itemView.findViewById(R.id.text_message_body_sent);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time_sent);
        }

        void bind(Message message) {
            messageText.setText(message.getMsg());
            timeText.setText(Tools.formatDateTime(message.getDateHeure()));
            //System.out.println("SentMessageHolder : Text Fitted");
        }
    }

    private class ReceivedMessageHolder extends MyViewHolder {
        TextView messageText, timeText, nameText;
        ImageView profileImage;

        ReceivedMessageHolder(View itemView) {
            super(itemView);
            messageText = (TextView) itemView.findViewById(R.id.text_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
            nameText = (TextView) itemView.findViewById(R.id.text_message_name);
            profileImage = (ImageView) itemView.findViewById(R.id.image_message_profile);

        }

        void bind(Message message) {
            messageText.setText(message.getMsg());
            timeText.setText(Tools.formatDateTime(message.getDateHeure()));
            nameText.setText("Simple Sender");
            //System.out.println("ReceivedMessageHolder : Text Fitted");
        }
    }
}