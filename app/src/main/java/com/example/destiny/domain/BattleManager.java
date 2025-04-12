package com.example.destiny.domain;

import com.example.destiny.domain.adventurer.Adventurer;
import com.example.destiny.domain.enemy.Enemy;

public class BattleManager {
    private final BattleViewModel viewModel;
    private Enemy enemy;
    private Adventurer adventurer;
    private String currentTextOutput;
    private BattleState battleState;

    BattleManager(Adventurer adventurer, Enemy enemy)
    {
        this.adventurer = adventurer;
        this.enemy = enemy;
        this.currentTextOutput = "";
        this.viewModel = new BattleViewModel();
    }

    public void executeFullTurn(boolean attackIsSpecial) {
        // execute player turn and take battle state
        this.battleState = this.playerTurn(attackIsSpecial);

        // update battle view model after player turn
        this.viewModel.updateBattleResult(
                new BattleResult(
                        this.adventurer,
                        this.enemy,
                        this.currentTextOutput,
                        this.battleState)
        );

        // if player hasn't won, continue to enemy turn
        if (this.battleState != BattleState.VICTORY)
        {
            // execute enemy turn and take battle state
            this.battleState = this.enemyTurn();

            // update battle view model after enemy turn
            this.viewModel.updateBattleResult(
                    new BattleResult(
                            this.adventurer,
                            this.enemy,
                            this.currentTextOutput,
                            this.battleState)
            );
        }
    }

    public BattleState playerTurn(boolean attackIsSpecial)
    {
        currentTextOutput = "";
        if(attackIsSpecial)
        {

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
            currentTextOutput += enemy.name + " has fallen!\n";
            return BattleState.VICTORY;
        }

        return BattleState.ONGOING;
    }

    public BattleState enemyTurn()
    {
        currentTextOutput = "";

        int enemyAttack = enemy.attack();

        // check if attack is critical
        if(enemyAttack == (int) Math.ceil(enemy.combatStats.attack * enemy.combatStats.critDamage))
        {
            currentTextOutput += "Critical hit! ";
        }


        int damage = adventurer.defend(enemyAttack, enemy.attackType);

        currentTextOutput += enemy.name + " dealt " + damage + "damage\n";

        // if adventurer died declare adventurer has fallen
        if(adventurer.combatStats.currentHealth <= 0)
        {
            currentTextOutput += adventurer.adventurerName + " has fallen!\n";
            return BattleState.DEFEAT;
        }

        return BattleState.ONGOING;
    }
}
