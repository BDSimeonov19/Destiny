package com.example.destiny.data.models.special;

import com.example.destiny.data.models.adventurer.CombatStatistics;

public class HeavyBlow extends SpecialAttack{

    public HeavyBlow(CombatStatistics combatStats) {
        super(combatStats, "Heavy blow");
        // cooldown
        super.maxCooldown = 2;
        super.currentCooldown = 0;

        // attack scaling of the attack
        super.attackScaling = 1.5f;
    }

    @Override
    public int attack() {
        int attackValue = (int) Math.ceil(combatStats.attack * attackScaling);

        // if critical hit lands, increase attack by crit damage
        if(Math.random() < combatStats.critRate)
        {
            attackValue = (int) Math.ceil(attackValue * combatStats.critDamage);
        }
        return attackValue;
    }
}
