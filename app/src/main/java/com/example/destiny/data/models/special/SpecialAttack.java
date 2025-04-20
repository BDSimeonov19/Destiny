package com.example.destiny.data.models.special;

import com.example.destiny.data.models.adventurer.CombatStatistics;

public abstract class SpecialAttack extends Special{
    public double attackScaling;
    public CombatStatistics combatStats;
    public SpecialAttack(CombatStatistics combatStats, String name) {
        this.combatStats = combatStats;
        super.name = name;
    }

    public abstract int attack();


}
