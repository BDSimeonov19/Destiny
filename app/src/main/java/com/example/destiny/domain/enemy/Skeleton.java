package com.example.destiny.domain.enemy;

import com.example.destiny.R;
import com.example.destiny.domain.adventurer.AttackType;
import com.example.destiny.domain.adventurer.CombatStatistics;

public class Skeleton extends Enemy{
    public Skeleton()
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
        super.spriteDrawableId = R.drawable.enemy_skeleton;
    }
}
