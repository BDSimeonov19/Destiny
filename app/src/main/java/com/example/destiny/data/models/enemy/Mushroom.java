package com.example.destiny.data.models.enemy;

import com.example.destiny.R;
import com.example.destiny.data.models.adventurer.AttackType;
import com.example.destiny.data.models.adventurer.CombatStatistics;

public class Mushroom extends Enemy{
    public Mushroom()
    {
        int attack = 6;
        int physicalResistance = 2;
        int magicalResistance = 4;
        int maxHealth = 17;
        int currentHealth = maxHealth;
        float critRate = 0.05f;
        float critDamage = 1.4f;

        super.combatStats = new CombatStatistics(attack, physicalResistance, magicalResistance, maxHealth, currentHealth, critRate, critDamage);

        super.attackType = AttackType.MAGICAL;
        super.monsterType = "Mushroom";
        super.spriteDrawableId = R.drawable.enemy_mushroom;
    }
}
