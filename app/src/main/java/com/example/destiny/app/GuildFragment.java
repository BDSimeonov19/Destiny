package com.example.destiny.app;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.destiny.MainActivity;
import com.example.destiny.R;
import com.example.destiny.domain.adventurers.Adventurer;
import com.example.destiny.domain.areas.Guild;

import java.util.ArrayList;
import java.util.HashMap;

public class GuildFragment extends Fragment {
    private ListView adventurersListView;
    private ArrayList<Adventurer> adventurers;
    private ArrayAdapter<Adventurer> adapter;
    private Button hireButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_guild, container, false);

        adventurersListView = view.findViewById(R.id.adventurersList);
        adventurers = Guild.getInstance().getAllAdventurers();
        adapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, adventurers);
        adventurersListView.setAdapter(adapter);


        hireButton = view.findViewById(R.id.hireButton);

        hireButton.setOnClickListener(
        new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHireAdventurerActivity(v);
            }
        }
        );

        return view;
    }

    private void openHireAdventurerActivity(View view)
    {
        Intent senderIntent = new Intent(view.getContext(), HireAdventurerActivity.class);
        startActivity(senderIntent);
    }
}