package com.example.nawras.smartroute;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nawras.smartroute.Beans.Message;
import com.example.nawras.smartroute.Helpers.MqttHelper;
import com.example.nawras.smartroute.WebMethods.ListeUserMessages;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Mes_messages extends Fragment {
    private RecyclerView mMessageRecycler;
    private List<Message> messageList,displayMessageList;
    private EditText messageText;
    private Button sendButton;
    private MqttHelper mqttHelper;
    SharedPreferences sharedPreferences;
    private int userID;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mes_messages, container, false);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        userID = sharedPreferences.getInt("userID", -1);
        messageList = new ArrayList<Message>();

        try
        {
            messageList = new ListeUserMessages(getActivity()).execute(userID).get();
        }
        catch (Exception e)
        {
            Toast.makeText(getContext(),"Erreur lors du récuperation des messages.",Toast.LENGTH_LONG).show();
        }
        displayMessageList = new ArrayList<Message>();
        for (Message m : messageList)
        {
            displayMessageList.add(m);
        }
        mqttHelper = new MqttHelper(Integer.toString(userID),getContext());
        mMessageRecycler = (RecyclerView) view.findViewById(R.id.reyclerview_message_list);
        messageText = (EditText) view.findViewById(R.id.edittext_chatbox);
        sendButton = (Button) view.findViewById(R.id.button_chatbox_send);
        final MessageListAdapter mMessageAdapter = new MessageListAdapter(getContext(), displayMessageList,userID);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mMessageRecycler.setAdapter(mMessageAdapter);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message m = new Message(0,userID,1,new Timestamp(System.currentTimeMillis()),messageText.getText().toString());
                if(mqttHelper.sendMessage(m))
                {
                    displayMessageList.add(m);
                    messageText.setText("");
                    mMessageAdapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getContext(),"Message non envoyé !",Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mqttHelper.unsubscribe();
    }

    @Override
    public void onResume() {
        super.onResume();
        mqttHelper.connect();
    }

    @Override
    public void onPause() {
        super.onPause();
        mqttHelper.unsubscribe();
    }
}