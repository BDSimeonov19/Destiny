package com.example.destiny.data.models.special;

import com.example.destiny.data.models.adventurer.Adventurer;
import com.example.destiny.data.models.adventurer.CombatStatistics;
import com.example.destiny.data.models.enemy.Enemy;

public abstract class SpecialEffect extends Special{
    public int maxDuration;
    public int currentDuration;
    public CombatStatistics combatStatistics;
    int countDown;
    public SpecialEffect(CombatStatistics combatStatistics) {
        this.combatStatistics = combatStatistics;
    }

    public abstract void applySpecialEffect(Adventurer adventurer, Enemy enemy);
    public abstract void executeSpecialEffect(Adventurer adventurer, Enemy enemy);
}
