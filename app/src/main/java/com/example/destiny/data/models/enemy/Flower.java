package com.example.destiny.data.models.enemy;

import com.example.destiny.R;
import com.example.destiny.data.models.adventurer.AttackType;
import com.example.destiny.data.models.adventurer.CombatStatistics;

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
        super.monsterType = "Flower";
        super.spriteDrawableId = R.drawable.enemy_flower;
    }
}
