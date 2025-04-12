package com.example.destiny.app;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.destiny.R;
import com.example.destiny.data.adventurer.Adventurer;
import com.example.destiny.data.enemy.Enemy;
import com.example.destiny.data.enemy.Flower;
import com.example.destiny.domain.area.Guild;
import com.example.destiny.domain.battle.BattleManager;

import java.util.UUID;

public class BattleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_battle);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // get intent extras and retrieve adventurer with id
        UUID adventurerId = UUID.fromString(getIntent().getStringExtra("adventurerId"));
        Adventurer adventurer = Guild.getInstance().getAdventurerById(adventurerId);

        // TODO: let the battle area handle the preparations for the battle

        Enemy enemy = new Flower();

        // get battle log text view
        TextView battleLog = findViewById(R.id.battleLogTextView);

        // find and set image resources to sprites
        ImageView adventurerImage = findViewById(R.id.adventurerImageView);
        ImageView enemyImage = findViewById(R.id.enemyImageView);

        adventurerImage.setImageResource(adventurer.getSpriteDrawableId());
        enemyImage.setImageResource(enemy.getSpriteDrawableId());


        // create battle manager
        BattleManager battleManager = new BattleManager(adventurer, enemy);

        // set up observer that receives updates when battle result is updated
        // and push updates to UI
        battleManager.battleResult.observe(this, battleResult -> {
            battleLog.append(battleResult.actionLogText);
        });


        // add event listener to basic attack button
        Button basicAttackButton = findViewById(R.id.basicAttackButton);

        basicAttackButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                battleManager.handleFullTurn(0);
            }
        });

        // add event listener to special attack button
        Button specialAttackButton = findViewById(R.id.specialAttackButton);

        specialAttackButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                battleManager.handleFullTurn(1);
            }
        });
    }
}