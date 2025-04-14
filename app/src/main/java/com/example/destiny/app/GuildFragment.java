package com.example.destiny.app;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.destiny.R;
import com.example.destiny.data.models.adventurer.Adventurer;
import com.example.destiny.domain.area.Guild;

import java.util.ArrayList;

public class GuildFragment extends Fragment {
    private ListView adventurersListView;
    private ArrayList<Adventurer> adventurers;
    private AdventurerBaseAdapter adapter;
    private Button hireButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_guild, container, false);

        // displaying the list with adventurers
        adventurersListView = view.findViewById(R.id.adventurersList);
        adventurers = Guild.getInstance().getAllAdventurers();
        adapter = new AdventurerBaseAdapter(getActivity().getApplicationContext(), adventurers);
        adventurersListView.setAdapter(adapter);

        // on click item from list logic

        adventurersListView.setOnItemClickListener(
        new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openAdventurerStatsActivity(view, adventurers.get(position));
            }
        }
        );



        // hire button logic
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

    private void openAdventurerStatsActivity(View view, Adventurer adventurer) {
        Intent senderIntent = new Intent(view.getContext(), AdventurerStatsActivity.class);
        senderIntent.putExtra("adventurerId", adventurer.id.toString());
        startActivity(senderIntent);
    }

    private void openHireAdventurerActivity(View view)
    {
        Intent senderIntent = new Intent(view.getContext(), HireAdventurerActivity.class);
        startActivity(senderIntent);
    }

    @Override
    public void onResume() {
        super.onResume();
        adventurers = Guild.getInstance().getAllAdventurers();
        adapter = new AdventurerBaseAdapter(getActivity().getApplicationContext(), adventurers);
        adventurersListView.setAdapter(adapter);
    }
}