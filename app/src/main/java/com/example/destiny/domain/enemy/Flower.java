package com.example.destiny.domain.enemy;

import com.example.destiny.domain.adventurer.AttackType;
import com.example.destiny.domain.adventurer.CombatStatistics;

public class Flower extends Enemy{
    public Flower()
    {
        int attack = 5;
        int physicalResistance = 2;
        int magicalResistance = 1;
        int maxHealth = 15;
        int currentHealth = maxHealth;
        float critRate = 0.05f;
        float critDamage = 1.4f;

        super.combatStats = new CombatStatistics(attack, physicalResistance, magicalResistance, maxHealth, currentHealth, critRate, critDamage);

        super.attackType = AttackType.PHYSICAL;
        //TODO: add sprite drawables
        //super.spriteDrawableId = R.drawable.sprite_assassin;
    }
}
