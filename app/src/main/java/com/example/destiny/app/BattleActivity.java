package com.example.destiny.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.example.destiny.MainActivity;
import com.example.destiny.R;
import com.example.destiny.data.adventurer.Adventurer;
import com.example.destiny.data.enemy.Enemy;
import com.example.destiny.data.enemy.Flower;
import com.example.destiny.domain.area.Area;
import com.example.destiny.domain.area.Battle;
import com.example.destiny.domain.area.Guild;
import com.example.destiny.domain.battle.BattleManager;
import com.example.destiny.domain.battle.BattleState;

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

        // get intent extras
        UUID adventurerId = UUID.fromString(getIntent().getStringExtra("adventurerId"));
        String difficulty = getIntent().getStringExtra("difficulty");

        // retrieve adventurer with id
        Adventurer adventurer = Guild.getInstance().getAdventurerById(adventurerId);

        // move adventurer to battle area
        Battle battle = Battle.getInstance();
        Area.moveAdventurer(adventurer.id, Guild.getInstance(), battle);

        // let the battle area handle the preparations for the battle
        battle.battleSetup(difficulty);

        // get battle log text view
        TextView battleLog = findViewById(R.id.battleLogTextView);

        // find and set image resources to sprites
        ImageView adventurerImage = findViewById(R.id.adventurerImageView);
        ImageView enemyImage = findViewById(R.id.enemyImageView);

        adventurerImage.setImageResource(adventurer.getSpriteDrawableId());
        enemyImage.setImageResource(battle.enemies.get(0).getSpriteDrawableId()); // first enemy's sprite is loaded


        // create battle manager
        BattleManager battleManager = new BattleManager(adventurer, battle.enemies);

        // set up observer that receives updates when battle result is updated
        // and push updates to UI
        battleManager.battleResult.observe(this, battleResult -> {
            // update battle log
            battleLog.append(battleResult.actionLogText);
            // set image to active enemy
            enemyImage.setImageResource(battleResult.activeEnemy.getSpriteDrawableId());

            if(battleResult.battleState == BattleState.VICTORY)
            {
                // move adventurer back to guild
                Area.moveAdventurer(adventurer.id, Battle.getInstance(), Guild.getInstance());
                createVictoryDialogBox("Victory");
            }

            if(battleResult.battleState == BattleState.DEFEAT)
            {
                // move adventurer back to guild
                Area.moveAdventurer(adventurer.id, Battle.getInstance(), Guild.getInstance());
                createVictoryDialogBox("Defeat");
            }
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

    private void createVictoryDialogBox(String messageText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BattleActivity.this);

        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent senderIntent = new Intent(BattleActivity.this, MainActivity.class);
                startActivity(senderIntent);
            }
        });

        builder.setMessage(messageText);

        builder.setCancelable(false);
        // because this stupid line above does not work, the official documentation
        // does a horrible job at providing coherent code snippets to follow for example
        // and the only information outside of that is 12 year old outdated stackoverflow posts
        // I am forced to apply the same functionality to the on cancel listener
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Intent senderIntent = new Intent(BattleActivity.this, MainActivity.class);
                startActivity(senderIntent);
            }
        });
        builder.create().show();
    }
}