package com.example.destiny.data.models.special;

import com.example.destiny.data.models.adventurer.Adventurer;
import com.example.destiny.data.models.adventurer.CombatStatistics;
import com.example.destiny.data.models.enemy.Enemy;

public class Enhance extends SpecialEffect
{
    private final int attackIncrease = 2;
    private final int physicalResistanceIncrease = 1;
    private final int magicalResistanceIncrease = 2;
    public Enhance(CombatStatistics combatStatistics) {
        super(combatStatistics, "Enhance");

        // cooldown
        super.maxCooldown = 4;
        super.currentCooldown = 0;

        super.maxDuration = 3;
        super.currentDuration = 0;
    }

    @Override
    public void applySpecialEffect(Adventurer adventurer, Enemy enemy) {
        adventurer.combatStats.attack += attackIncrease;
        adventurer.combatStats.physicalResistance += physicalResistanceIncrease;
        adventurer.combatStats.magicalResistance += magicalResistanceIncrease;

        currentDuration = maxDuration;
    }

    @Override
    public void executeSpecialEffect(Adventurer adventurer, Enemy enemy) {
        // if special effect is to expire this turn, remove buff
        if(currentDuration == 1)
        {
            removeSpecialEffect(adventurer, enemy);
        }
        // decrement duration by one turn
        currentDuration -= currentDuration > 0 ? 1 : 0;
    }

    @Override
    public void removeSpecialEffect(Adventurer adventurer, Enemy enemy) {
        adventurer.combatStats.attack -= attackIncrease;
        adventurer.combatStats.physicalResistance -= physicalResistanceIncrease;
        adventurer.combatStats.magicalResistance -= magicalResistanceIncrease;
    }

    @Override
    public String battleLogLine(Enemy enemy) {
        return "";
    }

}
