package com.example.destiny.domain;

import com.example.destiny.domain.adventurer.Adventurer;
import com.example.destiny.domain.enemy.Enemy;

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
