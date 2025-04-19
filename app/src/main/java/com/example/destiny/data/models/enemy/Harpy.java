package com.example.destiny.data.models.enemy;

import com.example.destiny.R;
import com.example.destiny.data.models.adventurer.AttackType;
import com.example.destiny.data.models.adventurer.CombatStatistics;

public class Harpy extends Enemy {
    public Harpy()
    {
        int attack = 8;
        int physicalResistance = 1;
        int magicalResistance = 3;
        int maxHealth = 18;
        int currentHealth = maxHealth;
        float critRate = 0.1f;
        float critDamage = 1.4f;

        super.combatStats = new CombatStatistics(attack, physicalResistance, magicalResistance, maxHealth, currentHealth, critRate, critDamage);

        super.attackType = AttackType.MAGICAL;
        super.monsterType = "Harpy";
        super.spriteDrawableId = R.drawable.enemy_harpy;
    }
}
