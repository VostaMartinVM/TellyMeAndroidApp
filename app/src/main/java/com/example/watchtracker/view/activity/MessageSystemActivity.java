package com.example.watchtracker.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.watchtracker.R;
import com.example.watchtracker.adapters.MessageListAdapter;

public class MessageSystemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_system);
        String[] personsName = {"Senpai", "OniChan<3", "Mommy"};
        String[] personsLastMessage = {"ajaja", "oniiiichan", "ara ara"};
        int[] personsProfilePicture = {R.mipmap.lists_background, R.mipmap.lists_background, R.mipmap.lists_background};
        ListView listView = (ListView) findViewById(R.id.message_list);
        MessageListAdapter messageListAdapter = new MessageListAdapter(getApplicationContext(), personsName, personsLastMessage, personsProfilePicture);
        listView.setAdapter(messageListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(MessageSystemActivity.this, MessagesActivity.class));
            }
        });
    }


}