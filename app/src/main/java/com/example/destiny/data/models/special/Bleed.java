package com.example.destiny.data.models.special;

import com.example.destiny.data.models.adventurer.Adventurer;
import com.example.destiny.data.models.adventurer.AttackType;
import com.example.destiny.data.models.adventurer.CombatStatistics;
import com.example.destiny.data.models.enemy.Enemy;

public class Bleed extends SpecialEffect
{
    private final double critRateIncrease = 0.30;
    private final int bleedOverTime = 3;
    private int bleedDamage = 0;
    public Bleed(CombatStatistics combatStatistics) {
        super(combatStatistics, "Bleed");

        // cooldown
        super.maxCooldown = 5;
        super.currentCooldown = 0;

        super.maxDuration = 3;
        super.currentDuration = 0;
    }

    @Override
    public void applySpecialEffect(Adventurer adventurer, Enemy enemy) {
        // give crit buff
        adventurer.combatStats.critRate += critRateIncrease;
        currentDuration = maxDuration;
    }

    @Override
    public void executeSpecialEffect(Adventurer adventurer, Enemy enemy) {
        // deal damage over time
        if(currentDuration != 0)
        {
            bleedDamage = enemy.defend(bleedOverTime, AttackType.PHYSICAL);
        }

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
        adventurer.combatStats.critRate -= critRateIncrease;
        bleedDamage = 0;
    }

    @Override
    public String battleLogLine(Enemy enemy) {
        if(bleedDamage != 0)
        {
            return enemy.monsterType + " bled for " + bleedDamage + " damage\n";
        }
        else
            return "";
    }
}
