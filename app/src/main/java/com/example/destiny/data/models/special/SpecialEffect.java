package com.example.destiny.data.models.special;

import com.example.destiny.data.models.adventurer.Adventurer;
import com.example.destiny.data.models.adventurer.CombatStatistics;
import com.example.destiny.data.models.enemy.Enemy;

public abstract class SpecialEffect extends Special{
    public int maxDuration;
    public int currentDuration;
    public CombatStatistics combatStatistics;
    public SpecialEffect(CombatStatistics combatStatistics, String name) {
        this.combatStatistics = combatStatistics;
        super.name = name;
    }

    public abstract void applySpecialEffect(Adventurer adventurer, Enemy enemy);
    public abstract void executeSpecialEffect(Adventurer adventurer, Enemy enemy);

    public abstract String battleLogLine(Enemy enemy);
}
