package com.example.destiny.data.models.special;

import com.example.destiny.data.models.adventurer.CombatStatistics;

public abstract class SpecialAttack extends Special{
    public float attackScaling;
    CombatStatistics combatStats;
    public SpecialAttack( CombatStatistics combatStats) {
        this.combatStats = combatStats;
    }

    public abstract int attack();


}
