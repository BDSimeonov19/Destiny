package com.example.destiny.domain.adventurer;

import com.example.destiny.domain.Records;

import java.util.UUID;

public abstract class Adventurer {
    public UUID id;
    public String adventurerName;
    public AttackType attackType;
    public String className;
    public int experience;
    public CombatStatistics combatStats;
    public Records records;
    public int specialCooldown;
    protected int spriteDrawableId;
    protected int iconDrawableId;

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
    public abstract int specialAttack();
    public void defend(int attack, AttackType attackType) {
        // reduce attack by defence for specific type
        attack = attackType == AttackType.PHYSICAL ?
                attack - combatStats.physicalResistance :
                attack - combatStats.magicalResistance;

        // clamp value to 0 if it is negative
        attack = Math.max(attack, 0);

        //reduce current health by attack
        this.combatStats.currentHealth -= attack;
    }

    public int getSpriteDrawableId()
    {
        return this.spriteDrawableId;
    }

    public int getIconDrawableId() { return this.iconDrawableId; }
}
