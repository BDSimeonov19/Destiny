package com.example.destiny.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.destiny.R;
import com.example.destiny.domain.adventurers.Adventurer;
import com.example.destiny.domain.areas.Guild;

import java.util.UUID;

public class AdventurerStatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adventurer_stats);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // make back button event listener
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(
            new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    getOnBackPressedDispatcher().onBackPressed();
                }
            }
        );


        // get intent extras and retrieve adventurer with id
        UUID adventurerId = UUID.fromString(getIntent().getStringExtra("adventurerId"));
        System.out.println(adventurerId);
        Adventurer adventurer = Guild.getInstance().getAdventurerById(adventurerId);

        // get relevant views in layout
        TextView adventurerNameTextView = findViewById(R.id.adventurerNameTextView);
        TextView classTextView = findViewById(R.id.classTextView);
        TextView attackTypeTextView = findViewById(R.id.attackTypeTextView);
        TextView experienceTextView = findViewById(R.id.experienceTextView);
        TextView attackTextView = findViewById(R.id.attackTextView);
        TextView maxHealthTextView = findViewById(R.id.maxHealthTextView);
        TextView physicalResistanceTextView = findViewById(R.id.physicalResistanceTextView);
        TextView magicalResistanceTextView = findViewById(R.id.magicalResistanceTextView);
        TextView victoriesTextView = findViewById(R.id.victoriesTextView);
        TextView totalBattlesTextView = findViewById(R.id.totalBattlesTextView);


        // set text in layout

        // make enum text lowercase with capitalised first letter
        String attackTypeText = adventurer.attackType.toString();
        attackTypeText = attackTypeText.substring(0, 1).toUpperCase() + attackTypeText.substring(1).toLowerCase();

        adventurerNameTextView.setText(adventurer.adventurerName);
        classTextView.setText(adventurer.className);
        attackTypeTextView.setText(attackTypeText);
        experienceTextView.setText(Integer.toString(adventurer.experience));
        attackTextView.setText(getString(R.string.attack, adventurer.combatStats.attack));
        maxHealthTextView.setText(getString(R.string.max_health, adventurer.combatStats.maxHealth));
        physicalResistanceTextView.setText(getString(R.string.physical_resistance, adventurer.combatStats.physicalResistance));
        magicalResistanceTextView.setText(getString(R.string.magical_resistance, adventurer.combatStats.magicalResistance));
        victoriesTextView.setText(getString(R.string.victories, adventurer.records.victories));
        totalBattlesTextView.setText(getString(R.string.total_battles, adventurer.records.battles));



    }
}