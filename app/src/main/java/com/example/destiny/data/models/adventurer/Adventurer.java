package com.example.destiny.data.models.adventurer;

import com.example.destiny.data.models.Records;
import com.example.destiny.data.models.special.Special;

import java.io.Serializable;
import java.util.UUID;

public abstract class Adventurer implements Serializable {
    public UUID id;
    public String adventurerName;
    public AttackType attackType;
    public String className;
    public int experience;
    public CombatStatistics combatStats;
    public Records records;
    public Special special;
    protected int spriteDrawableId;
    protected int iconDrawableId;
    public int specialDescriptionId;

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

    public int getSpriteDrawableId()
    {
        return this.spriteDrawableId;
    }

    public int getIconDrawableId() { return this.iconDrawableId; }
}
