package com.example.destiny.data.models.special;

import com.example.destiny.data.models.adventurer.CombatStatistics;

public class LifeSteal extends SpecialAttack{
    private int damage = 2;
    private int heal = 3;
    public LifeSteal(CombatStatistics combatStats) {
        super(combatStats, "Life Steal");
        // cooldown
        super.maxCooldown = 3;
        super.currentCooldown = 0;

        // attack scaling of the attack
        super.attackScaling = 0f;
    }

    @Override
    public int attack() {
        // attack does not crit and deals flat damage
        int attackValue = damage;


        // heal up to max health
        combatStats.currentHealth = Math.min(combatStats.currentHealth + heal, combatStats.maxHealth);
        return attackValue;
    }
}
