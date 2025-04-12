package com.example.destiny.domain.battle;

import com.example.destiny.data.adventurer.Adventurer;
import com.example.destiny.data.enemy.Enemy;

// this class is used to transfer data from the battle manager to the UI
public class BattleResult {
    public Adventurer adventurer;
    public Enemy enemy;
    public String actionLogText;
    public BattleState battleState;


    public BattleResult(Adventurer adventurer, Enemy enemy, String actionLogText, BattleState battleState) {
        this.adventurer = adventurer;
        this.enemy = enemy;
        this.actionLogText = actionLogText;
        this.battleState = battleState;
    }
}
