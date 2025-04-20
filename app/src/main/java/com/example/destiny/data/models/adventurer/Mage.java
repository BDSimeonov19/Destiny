package com.example.destiny.data.models.adventurer;

import com.example.destiny.R;
import com.example.destiny.data.models.special.Enhance;

public class Mage extends Adventurer{
    public Mage(String adventurerName)
    {
        int attack = 6;
        int physicalResistance = 2;
        int magicalResistance = 3;
        int maxHealth = 17;
        int currentHealth = maxHealth;
        float critRate = 0.05f;
        float critDamage = 1.4f;

        super.combatStats = new CombatStatistics(attack, physicalResistance, magicalResistance, maxHealth, currentHealth, critRate, critDamage);

        super.special = new Enhance(super.combatStats);

        super.id = java.util.UUID.randomUUID();
        super.adventurerName = adventurerName;
        super.attackType = AttackType.MAGICAL;
        super.className = "Mage";
        super.experience = 0;
        super.records = new Records(0, 0);
        super.spriteDrawableId = R.drawable.sprite_mage;
        super.iconDrawableId = R.drawable.icon_mage;
        super.specialDescriptionId = R.string.mage_special;
    }
}
