package com.example.destiny.data.models.adventurer;

import com.example.destiny.R;
import com.example.destiny.data.models.Records;

public class Berserker extends Adventurer{
    public Berserker(String adventurerName)
    {
        int attack = 8;
        int physicalResistance = 0;
        int magicalResistance = 0;
        int maxHealth = 22;
        int currentHealth = maxHealth;
        float critRate = 0.05f;
        float critDamage = 1.4f;

        super.combatStats = new CombatStatistics(attack, physicalResistance, magicalResistance, maxHealth, currentHealth, critRate, critDamage);

        super.id = java.util.UUID.randomUUID();
        super.adventurerName = adventurerName;
        super.attackType = AttackType.PHYSICAL;
        super.className = "Berserker";
        super.experience = 0;
        super.records = new Records(0, 0);
        super.spriteDrawableId = R.drawable.sprite_berserker;
        super.iconDrawableId = R.drawable.icon_berserker;
    }

    public int specialAttack() {
        return 0;
    }
}
