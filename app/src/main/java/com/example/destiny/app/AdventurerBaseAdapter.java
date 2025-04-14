package com.example.destiny.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.destiny.R;
import com.example.destiny.data.models.adventurer.Adventurer;

import java.util.ArrayList;

public class AdventurerBaseAdapter extends BaseAdapter {

    Context context;
    ArrayList<Adventurer> adventurers;
    LayoutInflater inflater;
    public AdventurerBaseAdapter(Context context, ArrayList<Adventurer> adventurers)
    {
        this.context = context;
        this.adventurers = adventurers;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return adventurers.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.adventurers_list_item, null);
        TextView adventurerNameTextView = convertView.findViewById(R.id.adventurerNameListTextView);
        TextView adventurerClassTextView = convertView.findViewById(R.id.adventurerClassListTextView);
        ImageView adventuerImageView = convertView.findViewById(R.id.adventurerImage);

        adventurerNameTextView.setText(this.adventurers.get(position).adventurerName);
        adventurerClassTextView.setText(this.adventurers.get(position).className);
        adventuerImageView.setImageResource(this.adventurers.get(position).getIconDrawableId());
        return convertView;
    }
}
