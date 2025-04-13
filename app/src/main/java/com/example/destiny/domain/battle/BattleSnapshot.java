package com.example.destiny.domain.battle;

import com.example.destiny.data.adventurer.Adventurer;
import com.example.destiny.data.enemy.Enemy;

// this class is used to transfer data from the battle manager to the UI
public class BattleSnapshot {
    public Adventurer adventurer;
    public Enemy activeEnemy;
    public String actionLogText;
    public BattleState battleState;
    public TurnState turnState;


    public BattleSnapshot(Adventurer adventurer, Enemy enemy, String actionLogText, BattleState battleState, TurnState turnState) {
        this.adventurer = adventurer;
        this.activeEnemy = enemy;
        this.actionLogText = actionLogText;
        this.battleState = battleState;
        this.turnState = turnState;
    }
}
