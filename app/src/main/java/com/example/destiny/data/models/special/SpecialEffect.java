package com.example.destiny.data.models.special;

import com.example.destiny.data.models.adventurer.Adventurer;
import com.example.destiny.data.models.adventurer.CombatStatistics;
import com.example.destiny.data.models.enemy.Enemy;

public abstract class SpecialEffect extends Special{
    public int maxDuration;
    public int currentDuration;
    public CombatStatistics combatStats;
    public SpecialEffect(CombatStatistics combatStats, String name) {
        this.combatStats = combatStats;
        super.name = name;
    }

    public abstract void applySpecialEffect(Adventurer adventurer, Enemy enemy);
    public abstract void executeSpecialEffect(Adventurer adventurer, Enemy enemy);
    public abstract void removeSpecialEffect(Adventurer adventurer, Enemy enemy);
    public abstract String battleLogLine(Enemy enemy);
}
