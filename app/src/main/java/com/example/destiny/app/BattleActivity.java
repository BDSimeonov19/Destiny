package com.example.destiny.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.destiny.MainActivity;
import com.example.destiny.R;
import com.example.destiny.data.models.adventurer.Adventurer;
import com.example.destiny.domain.area.Area;
import com.example.destiny.domain.area.Battle;
import com.example.destiny.domain.area.Guild;
import com.example.destiny.domain.battle.BattleManager;
import com.example.destiny.domain.battle.BattleState;
import com.example.destiny.domain.battle.TurnState;

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

        // get relevant views
        Button basicAttackButton = findViewById(R.id.basicAttackButton);
        Button specialAttackButton = findViewById(R.id.specialAttackButton);
        ImageView adventurerImage = findViewById(R.id.adventurerImageView);
        ImageView enemyImage = findViewById(R.id.enemyImageView);
        TextView adventurerHealth = findViewById(R.id.adventurerHealthTextView);
        TextView enemyHealth = findViewById(R.id.enemyHealthTextView);
        ScrollView scrollView = findViewById(R.id.scrollView2);

        // set image resources to sprites
        adventurerImage.setImageResource(adventurer.getSpriteDrawableId());
        enemyImage.setImageResource(battle.enemies.get(0).getSpriteDrawableId()); // first enemy's sprite is loaded

        // set health text
        String startAdventurerHealthText = adventurer.combatStats.currentHealth + "/" + adventurer.combatStats.maxHealth;
        String startEnemyHealthText = battle.enemies.get(0).combatStats.currentHealth + "/" + battle.enemies.get(0).combatStats.maxHealth;
        adventurerHealth.setText(startAdventurerHealthText);
        enemyHealth.setText(startEnemyHealthText);

        // create battle manager
        BattleManager battleManager = new BattleManager(adventurer, battle.enemies);

        // set up observer that receives updates when battle result is updated
        // and push updates to UI
        battleManager.battleSnapshot.observe(this, battleSnapshot -> {
            // update battle log
            battleLog.append(battleSnapshot.actionLogText);
            // scroll down to bottom
            scrollView.fullScroll(View.FOCUS_DOWN);
            // set image to active enemy
            enemyImage.setImageResource(battleSnapshot.activeEnemy.getSpriteDrawableId());

            // set health
            String adventurerHealthText = battleSnapshot.adventurer.combatStats.currentHealth + "/" + battleSnapshot.adventurer.combatStats.maxHealth;
            String enemyHealthText = battleSnapshot.activeEnemy.combatStats.currentHealth + "/" + battleSnapshot.activeEnemy.combatStats.maxHealth;
            adventurerHealth.setText(adventurerHealthText);
            enemyHealth.setText(enemyHealthText);


            // enable buttons on player turn
            if(battleSnapshot.turnState == TurnState.PLAYER_TURN)
            {
                basicAttackButton.setActivated(true);
                //TODO: make custom enabled and disabled style for button and change between them when player doesn't have control
                //basicAttackButton.setBackgroundTintList(ColorStateList.valueOf(0x8C47D1));
                specialAttackButton.setActivated(true);
                //specialAttackButton.setBackgroundTintList(ColorStateList.valueOf(0x8C47D1));
            }

            // disable buttons on enemy turn
            if(battleSnapshot.turnState == TurnState.ENEMY_TURN)
            {
                basicAttackButton.setActivated(false);
                //basicAttackButton.setBackgroundTintList(ColorStateList.valueOf(0xBCBBBD));
                specialAttackButton.setActivated(false);
                //specialAttackButton.setBackgroundTintList(ColorStateList.valueOf(0xBCBBBD));
            }

            if(battleSnapshot.battleState == BattleState.VICTORY)
            {
                // move adventurer back to guild
                Area.moveAdventurer(adventurer.id, Battle.getInstance(), Guild.getInstance());
                createVictoryDialogBox("Victory");
            }

            if(battleSnapshot.battleState == BattleState.DEFEAT)
            {
                // move adventurer back to guild
                Area.moveAdventurer(adventurer.id, Battle.getInstance(), Guild.getInstance());
                createVictoryDialogBox("Defeat");
            }
        });


        // add event listener to basic attack button
        basicAttackButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                battleManager.handleFullTurn(0);
            }
        });

        // add event listener to special attack button
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
        // the line above alone does not work. The official documentation
        // does a horrible job at providing coherent code snippets to follow for example
        // and the only information outside of that is 12 year old outdated stackoverflow posts.
        // I am forced to apply the same functionality to the on cancel listener to make it work for some reason
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