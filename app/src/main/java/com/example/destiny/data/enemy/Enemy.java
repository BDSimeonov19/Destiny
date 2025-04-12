package com.example.destiny.data.enemy;

import com.example.destiny.data.adventurer.AttackType;
import com.example.destiny.data.adventurer.CombatStatistics;

public abstract class Enemy {
    public String name;
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

        // clamp value to 0 if it is negative
        attack = Math.max(attack, 0);

        //reduce current health by attack
        this.combatStats.currentHealth -= attack;

        return attack;
    }

    public int getSpriteDrawableId() { return this.spriteDrawableId; }
}
