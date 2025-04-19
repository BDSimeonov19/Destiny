package com.example.destiny.data.models.special;

import com.example.destiny.data.models.adventurer.Adventurer;
import com.example.destiny.data.models.adventurer.AttackType;
import com.example.destiny.data.models.adventurer.CombatStatistics;
import com.example.destiny.data.models.enemy.Enemy;

public class Bleed extends SpecialEffect
{
    private final float critRateIncrease = 0.30f;
    private final int bleedOverTime = 3;
    public int bleedDamage;
    public Bleed(CombatStatistics combatStatistics) {
        super(combatStatistics, "Bleed");

        // cooldown
        super.maxCooldown = 4;
        super.currentCooldown = 0;

        super.maxDuration = 2;
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
            adventurer.combatStats.critRate -= critRateIncrease;
        }
        // decrement duration by one turn
        currentDuration -= currentDuration > 0 ? 1 : 0;
    }

    @Override
    public String battleLogLine(Enemy enemy) {
        return enemy.monsterType + " bled for " + bleedDamage + " damage\n";
    }
}
