package com.example.destiny.data.models.adventurer;

import com.example.destiny.R;
import com.example.destiny.data.models.Records;

public class Knight extends Adventurer{
    public Knight(String adventurerName)
    {
        int attack = 6;
        int physicalResistance = 3;
        int magicalResistance = 2;
        int maxHealth = 17;
        int currentHealth = maxHealth;
        float critRate = 0.05f;
        float critDamage = 1.4f;

        super.combatStats = new CombatStatistics(attack, physicalResistance, magicalResistance, maxHealth, currentHealth, critRate, critDamage);

        super.id = java.util.UUID.randomUUID();
        super.adventurerName = adventurerName;
        super.attackType = AttackType.PHYSICAL;
        super.className = "Knight";
        super.experience = 0;
        // TODO: add records
        super.records = new Records(0, 0);
        super.specialCooldown = 0;
        super.spriteDrawableId = R.drawable.sprite_knight;
        super.iconDrawableId = R.drawable.icon_knight;
    }

    public int specialAttack() {
        return 0;
    }
}
