package com.example.destiny.data.models.enemy;

import com.example.destiny.data.models.adventurer.AttackType;
import com.example.destiny.data.models.adventurer.CombatStatistics;

public abstract class Enemy {
    public String monsterType;
    public AttackType attackType;
    public CombatStatistics combatStats;
    protected int spriteDrawableId;

    public int attack()
    {
        int attackValue = this.combatStats.attack;

        // if critical hit lands, increase attack by crit damage
        if(Math.random() < this.combatStats.critRate)
        {
            attackValue = (int) Math.ceil(attackValue * this.combatStats.critDamage);
        }
        return attackValue;
    }

    public int defend(int attack, AttackType attackType) {
        // reduce attack by defence for specific type
        attack = attackType == AttackType.PHYSICAL ?
                attack - combatStats.physicalResistance :
                attack - combatStats.magicalResistance;

        // clamp value to 1 if it is below 1
        attack = Math.max(attack, 1);

        // reduce current health by attack to a minimum of 0
        combatStats.currentHealth = Math.max(combatStats.currentHealth - attack, 0);

        return attack;
    }

    public int getSpriteDrawableId() { return this.spriteDrawableId; }
}
