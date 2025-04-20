package com.example.destiny.domain.battle;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.MutableLiveData;

import com.example.destiny.data.models.adventurer.Adventurer;
import com.example.destiny.data.models.enemy.Enemy;
import com.example.destiny.data.models.special.SpecialAttack;
import com.example.destiny.data.models.special.SpecialEffect;

import java.util.ArrayList;

public class BattleManager {
    // live data so the UI can be updated throughout the execution of battle
    public MutableLiveData<BattleSnapshot> battleSnapshot = new MutableLiveData<>();
    private Enemy activeEnemy;
    private ArrayList<Enemy> enemies;
    private Adventurer adventurer;
    private String currentTextOutput;
    private BattleState battleState;
    private TurnState turnState;

    public BattleManager(Adventurer adventurer, ArrayList<Enemy> enemies)
    {
        this.adventurer = adventurer;
        this.enemies = enemies;
        currentTextOutput = "";
        turnState = TurnState.PLAYER_TURN;
        loadNewEnemy();
    }

    public void handleFullTurn(int attackIsSpecial)
    {
        // if this function has been called not on a player turn, return
        if(turnState != TurnState.PLAYER_TURN){
            return;
        }
        // execute player turn and take battle state
        handlePlayerTurn(attackIsSpecial);
        // hand off turn to enemy
        turnState = TurnState.ENEMY_TURN;

        // load new enemy if current battle is victorious
        if(battleState == BattleState.VICTORY)
        {
            loadNewEnemy();
        }

        // update battle result after player turn
        battleSnapshot.setValue(
                new BattleSnapshot(
                        adventurer,
                        activeEnemy,
                        currentTextOutput,
                        battleState,
                        turnState
                )
        );

        // add delay to enemy turn
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // if player hasn't won, continue to enemy turn
                if (battleState != BattleState.VICTORY)
                {
                    // execute enemy turn
                    handleEnemyTurn();
                    // hand off turn to player
                    turnState = TurnState.PLAYER_TURN;

                    // update battle result after enemy turn
                    battleSnapshot.setValue(
                            new BattleSnapshot(
                                    adventurer,
                                    activeEnemy,
                                    currentTextOutput,
                                    battleState,
                                    turnState
                            )
                    );
                }

            }
        }, 1000);
    }

    private void loadNewEnemy()
    {
        // if there are enemies left
        if(!enemies.isEmpty())
        {
            activeEnemy = enemies.get(0);
            enemies.remove(0);
            currentTextOutput = activeEnemy.monsterType + " has appeared!\n";
            // set battle state to ongoing as battle is not finished
            battleState = BattleState.ONGOING;
        }
    }

    private void handlePlayerTurn(int attackIsSpecial)
    {
        currentTextOutput = "";
        if(attackIsSpecial == 1)
        {
            // handle special attacks
            if(adventurer.special instanceof SpecialAttack)
            {
                SpecialAttack specialAttack = (SpecialAttack) adventurer.special;

                // set cooldown to max
                specialAttack.currentCooldown = specialAttack.maxCooldown;

                // handle damage
                int adventurerAttack = specialAttack.attack();

                // check if attack is critical
                if(adventurerAttack == (int) Math.ceil(Math.ceil(adventurer.combatStats.attack * specialAttack.attackScaling) * adventurer.combatStats.critDamage))
                {
                    currentTextOutput += "Critical hit! ";
                }


                int damage = activeEnemy.defend(adventurerAttack, adventurer.attackType);

                currentTextOutput += adventurer.adventurerName + " used " + adventurer.special.name + " to deal " + damage + " damage\n";
            }

            // handle special effects
            if(adventurer.special instanceof SpecialEffect)
            {
                SpecialEffect specialEffect = (SpecialEffect) adventurer.special;

                // apply/initiate special effect
                specialEffect.applySpecialEffect(adventurer, activeEnemy);

                currentTextOutput += adventurer.adventurerName + " used " + adventurer.special.name + "\n";

                // set cooldown to max
                specialEffect.currentCooldown = specialEffect.maxCooldown;
            }
        }
        else
        {
            // decrease special ability cooldown
            adventurer.special.currentCooldown -= adventurer.special.currentCooldown > 0 ? 1 : 0;

            // handle base attack
            int adventurerAttack = adventurer.attack();

            // check if attack is critical
            if(adventurerAttack == (int) Math.ceil(adventurer.combatStats.attack * adventurer.combatStats.critDamage))
            {
                currentTextOutput += "Critical hit! ";
            }


            int damage = activeEnemy.defend(adventurerAttack, adventurer.attackType);

            currentTextOutput += adventurer.adventurerName + " dealt " + damage + " damage\n";
        }

        // execute a special effect "turn"
        if(adventurer.special instanceof SpecialEffect)
        {
            SpecialEffect specialEffect = (SpecialEffect) adventurer.special;

            // execute action
            specialEffect.executeSpecialEffect(adventurer, activeEnemy);

            // take text output of action
            currentTextOutput += specialEffect.battleLogLine(activeEnemy);
        }


        // if enemy died declare enemy has fallen
        if(activeEnemy.combatStats.currentHealth <= 0)
        {
            currentTextOutput += activeEnemy.monsterType + " has fallen!\n";
            battleState = BattleState.VICTORY;
            adventurer.records.battles += 1;
            adventurer.records.victories += 1;
            adventurer.experience += 1;

            return;
        }

        battleState = BattleState.ONGOING;
    }

    private void handleEnemyTurn()
    {
        currentTextOutput = "";

        int enemyAttack = activeEnemy.attack();

        // check if attack is critical
        if(enemyAttack == (int) Math.ceil(activeEnemy.combatStats.attack * activeEnemy.combatStats.critDamage))
        {
            currentTextOutput += "Critical hit! ";
        }


        int damage = adventurer.defend(enemyAttack, activeEnemy.attackType);

        currentTextOutput += activeEnemy.monsterType + " dealt " + damage + " damage\n";

        // if adventurer died declare adventurer has fallen
        if(adventurer.combatStats.currentHealth <= 0)
        {
            currentTextOutput += adventurer.adventurerName + " has fallen!\n";
            battleState = BattleState.DEFEAT;
            adventurer.records.battles += 1;
            return;
        }

        battleState = BattleState.ONGOING;
    }
}
