package com.example.destiny.domain.battle;

import androidx.lifecycle.MutableLiveData;

import com.example.destiny.data.adventurer.Adventurer;
import com.example.destiny.data.enemy.Enemy;
import com.example.destiny.domain.area.Area;
import com.example.destiny.domain.area.Battle;
import com.example.destiny.domain.area.Guild;

public class BattleManager {
    // live data so the UI can be updated throughout the execution of battle
    public MutableLiveData<BattleResult> battleResult = new MutableLiveData<>();
    private Enemy enemy;
    private Adventurer adventurer;
    private String currentTextOutput;
    private BattleState battleState;

    public BattleManager(Adventurer adventurer, Enemy enemy)
    {
        this.adventurer = adventurer;
        this.enemy = enemy;
        this.currentTextOutput = "";

        // move adventurer to battle area
    }

    public void handleFullTurn(int attackIsSpecial)
    {
        // execute player turn and take battle state
        this.handlePlayerTurn(attackIsSpecial);

        // update battle result after player turn
        this.battleResult.setValue(
                new BattleResult(
                        this.adventurer,
                        this.enemy,
                        this.currentTextOutput,
                        this.battleState
                )
        );

        // TODO: add delay between turns
        // if player hasn't won, continue to enemy turn
        if (this.battleState != BattleState.VICTORY)
        {
            // execute enemy turn and take battle state
            this.handleEnemyTurn();

            // update battle result after enemy turn
            this.battleResult.setValue(
                    new BattleResult(
                            this.adventurer,
                            this.enemy,
                            this.currentTextOutput,
                            this.battleState
                    )
            );
        }

        // if fight has ended, update stats of player
        if (this.battleState != BattleState.ONGOING)
        {
            adventurer.records.battles += 1;
            adventurer.records.victories += (this.battleState == BattleState.VICTORY) ? 1 : 0;
        }
    }

    private void handlePlayerTurn(int attackIsSpecial)
    {
        currentTextOutput = "";
        if(attackIsSpecial == 1)
        {
            //TODO:add special attacks and consider special effects
        }
        else
        {
            // handle base attack
            int adventurerAttack = adventurer.attack();

            // check if attack is critical
            if(adventurerAttack == (int) Math.ceil(adventurer.combatStats.attack * adventurer.combatStats.critDamage))
            {
                currentTextOutput += "Critical hit! ";
            }


            int damage = enemy.defend(adventurerAttack, adventurer.attackType);

            currentTextOutput += adventurer.adventurerName + " dealt " + damage + " damage\n";
        }


        // if adventurer died declare adventurer has fallen
        if(enemy.combatStats.currentHealth <= 0)
        {
            currentTextOutput += enemy.monsterType + " has fallen!\n";
            this.battleState = BattleState.VICTORY;
            return;
        }

        this.battleState = BattleState.ONGOING;
    }

    private void handleEnemyTurn()
    {
        currentTextOutput = "";

        int enemyAttack = enemy.attack();

        // check if attack is critical
        if(enemyAttack == (int) Math.ceil(enemy.combatStats.attack * enemy.combatStats.critDamage))
        {
            currentTextOutput += "Critical hit! ";
        }


        int damage = adventurer.defend(enemyAttack, enemy.attackType);

        currentTextOutput += enemy.monsterType + " dealt " + damage + " damage\n";

        // if adventurer died declare adventurer has fallen
        if(adventurer.combatStats.currentHealth <= 0)
        {
            currentTextOutput += adventurer.adventurerName + " has fallen!\n";
            this.battleState = BattleState.DEFEAT;
            return;
        }

        this.battleState = BattleState.ONGOING;
    }
}
