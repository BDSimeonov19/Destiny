package com.example.destiny.app;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.destiny.R;
import com.example.destiny.data.models.adventurer.Adventurer;
import com.example.destiny.domain.area.Area;
import com.example.destiny.domain.area.Guild;
import com.example.destiny.domain.area.Training;

import java.util.UUID;

public class TrainingActivity extends AppCompatActivity {

    // get relevant views in layout
    TextView adventurerNameTextView;
    TextView classTextView;
    TextView attackTypeTextView;
    TextView experienceTextView;
    TextView attackTextView;
    TextView maxHealthTextView;
    TextView physicalResistanceTextView;
    TextView magicalResistanceTextView;
    TextView critTextView;
    ImageButton attackPlusButton;
    ImageButton physicalResistancePlusButton;
    ImageButton magicalResistancePlusButton;
    ImageButton maxHealthPlusButton;
    ImageButton critRatePlusButton;

    Adventurer adventurer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_training);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // get intent extras and retrieve adventurer with id
        UUID adventurerId = UUID.fromString(getIntent().getStringExtra("adventurerId"));
        adventurer = Guild.getInstance().getAdventurerById(adventurerId);
        Area.moveAdventurer(adventurerId, Guild.getInstance(), Training.getInstance());

        // get views
        adventurerNameTextView = findViewById(R.id.adventurerNameTextView);
        classTextView = findViewById(R.id.classTextView);
        attackTypeTextView = findViewById(R.id.attackTypeTextView);
        experienceTextView = findViewById(R.id.experienceTextView);
        attackTextView = findViewById(R.id.attackTextView);
        maxHealthTextView = findViewById(R.id.maxHealthTextView);
        physicalResistanceTextView = findViewById(R.id.physicalResistanceTextView);
        magicalResistanceTextView = findViewById(R.id.magicalResistanceTextView);
        critTextView = findViewById(R.id.critTextView);
        attackPlusButton = findViewById(R.id.attackPlusButton);
        physicalResistancePlusButton = findViewById(R.id.physicalResistancePlusButton);
        magicalResistancePlusButton = findViewById(R.id.magicalResistancePlusButton);
        maxHealthPlusButton = findViewById(R.id.maxHealthPlusButton);
        critRatePlusButton = findViewById(R.id.critRatePlusButton);


        // set text in UI
        updateUIElements();

        // make back button event listener
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // move adventurer back to guild
                Area.moveAdventurer(adventurerId, Training.getInstance(), Guild.getInstance());
                getOnBackPressedDispatcher().onBackPressed();
            }
        });

        // make plus buttons event listeners

        attackPlusButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // upgrade stat if adventurer has enough experience
                adventurer.combatStats.attack += adventurer.experience > 0 ? 1 : 0;
                adventurer.experience -= adventurer.experience > 0 ? 1 : 0;
                updateUIElements();
            }
        });

        physicalResistancePlusButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // upgrade stat if adventurer has enough experience
                adventurer.combatStats.physicalResistance += adventurer.experience > 0 ? 1 : 0;
                adventurer.experience -= adventurer.experience > 0 ? 1 : 0;
                updateUIElements();
            }
        });

        magicalResistancePlusButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // upgrade stat if adventurer has enough experience
                adventurer.combatStats.magicalResistance += adventurer.experience > 0 ? 1 : 0;
                adventurer.experience -= adventurer.experience > 0 ? 1 : 0;
                updateUIElements();
            }
        });

        maxHealthPlusButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // upgrade stat if adventurer has enough experience
                adventurer.combatStats.maxHealth += adventurer.experience > 0 ? 1 : 0;
                adventurer.experience -= adventurer.experience > 0 ? 1 : 0;
                updateUIElements();
            }
        });

        critRatePlusButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // upgrade stat if adventurer has enough experience
                adventurer.combatStats.critRate += adventurer.experience > 0 ? 0.01f : 0f;
                adventurer.experience -= adventurer.experience > 0 ? 1 : 0;
                updateUIElements();
            }
        });
    }


    // set text in layout
    private void updateUIElements() {
        // make enum text lowercase with capitalised first letter
        String attackTypeText = adventurer.attackType.toString();
        attackTypeText = attackTypeText.substring(0, 1).toUpperCase() + attackTypeText.substring(1).toLowerCase();

        adventurerNameTextView.setText(adventurer.adventurerName);
        classTextView.setText(getString(R.string.class_name, adventurer.className));
        attackTypeTextView.setText(getString(R.string.attack_type, attackTypeText));
        experienceTextView.setText(getString(R.string.experience, adventurer.experience));
        attackTextView.setText(getString(R.string.attack, adventurer.combatStats.attack));
        maxHealthTextView.setText(getString(R.string.max_health, adventurer.combatStats.maxHealth));
        physicalResistanceTextView.setText(getString(R.string.physical_resistance, adventurer.combatStats.physicalResistance));
        magicalResistanceTextView.setText(getString(R.string.magical_resistance, adventurer.combatStats.magicalResistance));
        critTextView.setText(getString(R.string.crit_rate, (int) (adventurer.combatStats.critRate*100)));
    }
}