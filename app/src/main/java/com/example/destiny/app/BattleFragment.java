package com.example.destiny.app;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.destiny.R;
import com.example.destiny.data.adventurer.Adventurer;
import com.example.destiny.domain.area.Guild;

import java.util.ArrayList;

public class BattleFragment extends Fragment {
    private ListView adventurersListView;
    private ArrayList<Adventurer> adventurers;
    private AdventurerBaseAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_battle, container, false);

        // displaying the list with adventurers
        adventurersListView = view.findViewById(R.id.adventurersList);
        adventurers = Guild.getInstance().getAllAdventurers();
        adapter = new AdventurerBaseAdapter(getActivity().getApplicationContext(), adventurers);
        adventurersListView.setAdapter(adapter);
        adventurersListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // on click item from list logic

        adventurersListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openBattleActivity(view, adventurers.get(position));
            }
        });

        return view;
    }

    private void openBattleActivity(View view, Adventurer adventurer) {

        // get selected radio button
        RadioGroup difficultyRadioGroup = getView().findViewById(R.id.difficultyRadioGroup);
        RadioButton classTypeRadio = getView().findViewById(difficultyRadioGroup.getCheckedRadioButtonId());

        // open battle activity
        Intent senderIntent = new Intent(view.getContext(), BattleActivity.class);
        senderIntent.putExtra("adventurerId", adventurer.id.toString());
        senderIntent.putExtra("difficulty", classTypeRadio.getText());
        startActivity(senderIntent);
    }
}