package com.example.project1.ui.chat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.R;

import java.util.ArrayList;
import java.util.List;

public class Chat extends AppCompatActivity {
    private List<com.example.project1.ui.chat.Msg> msgList = new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private com.example.project1.ui.chat.MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_chat);

        initMsgs();
        inputText = findViewById(R.id.input_text);
        send = findViewById(R.id.send_button);
        msgRecyclerView = findViewById(R.id.msg_chatting_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter = new com.example.project1.ui.chat.MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = inputText.getText().toString();
                if(!content.isEmpty()){
                    com.example.project1.ui.chat.Msg msg = new com.example.project1.ui.chat.Msg(content, com.example.project1.ui.chat.Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size() - 1);
                    msgRecyclerView.scrollToPosition(msgList.size() - 1);
                    inputText.setText("");
                }
            }
        });
    }

    private void initMsgs(){
        com.example.project1.ui.chat.Msg msg1 = new com.example.project1.ui.chat.Msg("Hello guy.", com.example.project1.ui.chat.Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        com.example.project1.ui.chat.Msg msg2 = new com.example.project1.ui.chat.Msg("Who are you?", com.example.project1.ui.chat.Msg.TYPE_SENT);
        msgList.add(msg2);
        com.example.project1.ui.chat.Msg msg3 = new com.example.project1.ui.chat.Msg("I'm Tiger.", com.example.project1.ui.chat.Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }

}
