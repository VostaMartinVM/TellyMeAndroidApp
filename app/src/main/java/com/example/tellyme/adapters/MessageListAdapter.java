package com.example.tellyme.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tellyme.R;

public class MessageListAdapter extends BaseAdapter {

    Context context;
    String[] personsName;
    String[] personsLastMessage;
    int[] personsProfilePicture;
    LayoutInflater inflater;


    public MessageListAdapter(Context context, String[] personsName, String[] personsLastMessage, int [] personsProfilePicture)
    {
        this.personsName = personsName;
        this.personsLastMessage = personsLastMessage;
        this.personsProfilePicture = personsProfilePicture;
        this.context = context;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return personsName.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.messages_item, null);
        TextView personsNameView = (TextView) view.findViewById(R.id.message_username);
        TextView personsLastMsg = (TextView) view.findViewById(R.id.message_user_message);
        ImageView imageView = (ImageView) view.findViewById(R.id.program_image);
        personsNameView.setText(personsName[i]);
        personsLastMsg.setText(personsLastMessage[i]);
        imageView.setImageResource(personsProfilePicture[i]);
        return view;
    }
}
