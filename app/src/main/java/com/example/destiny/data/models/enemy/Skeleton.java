package com.example.destiny.data.models.enemy;

import com.example.destiny.R;
import com.example.destiny.data.models.adventurer.AttackType;
import com.example.destiny.data.models.adventurer.CombatStatistics;

public class Skeleton extends Enemy{
    public Skeleton()
    {
        int attack = 5;
        int physicalResistance = 3;
        int magicalResistance = 3;
        int maxHealth = 15;
        int currentHealth = maxHealth;
        float critRate = 0.05f;
        float critDamage = 1.4f;

        super.combatStats = new CombatStatistics(attack, physicalResistance, magicalResistance, maxHealth, currentHealth, critRate, critDamage);

        super.attackType = AttackType.PHYSICAL;
        super.monsterType = "Skeleton";
        super.spriteDrawableId = R.drawable.enemy_skeleton;
    }
}
