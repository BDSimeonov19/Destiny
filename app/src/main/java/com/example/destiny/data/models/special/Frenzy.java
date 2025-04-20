package com.example.destiny.data.models.special;

import com.example.destiny.data.models.adventurer.Adventurer;
import com.example.destiny.data.models.adventurer.CombatStatistics;
import com.example.destiny.data.models.enemy.Enemy;

public class Frenzy extends SpecialEffect
{
    private int attackIncrease;
    private int physicalResistanceReduced;
    private int magicalResistanceReduced;
    public Frenzy(CombatStatistics combatStatistics) {
        super(combatStatistics, "Frenzy");

        // cooldown
        super.maxCooldown = 5;
        super.currentCooldown = 0;

        super.maxDuration = 3;
        super.currentDuration = 0;
    }

    @Override
    public void applySpecialEffect(Adventurer adventurer, Enemy enemy) {
        // reduce resistances
        physicalResistanceReduced = adventurer.combatStats.physicalResistance;
        magicalResistanceReduced = adventurer.combatStats.magicalResistance;
        adventurer.combatStats.physicalResistance = 0;
        adventurer.combatStats.magicalResistance = 0;

        // give atk buff scaling off of resistances reduced
        attackIncrease = (int) Math.ceil((physicalResistanceReduced + magicalResistanceReduced)/4f);
        adventurer.combatStats.attack += attackIncrease;
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
        adventurer.combatStats.physicalResistance += physicalResistanceReduced;
        adventurer.combatStats.magicalResistance += magicalResistanceReduced;
    }

    @Override
    public String battleLogLine(Enemy enemy) {
        return "";
    }
}
