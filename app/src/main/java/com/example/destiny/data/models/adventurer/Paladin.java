package com.example.destiny.data.models.adventurer;

import com.example.destiny.R;
import com.example.destiny.data.models.special.Block;

public class Paladin extends Adventurer{
    public Paladin(String adventurerName)
    {
        int attack = 5;
        int physicalResistance = 5;
        int magicalResistance = 4;
        int maxHealth = 13;
        int currentHealth = maxHealth;
        float critRate = 0.03f;
        float critDamage = 1.4f;

        super.combatStats = new CombatStatistics(attack, physicalResistance, magicalResistance, maxHealth, currentHealth, critRate, critDamage);

        super.special = new Block(super.combatStats);

        super.id = java.util.UUID.randomUUID();
        super.adventurerName = adventurerName;
        super.attackType = AttackType.PHYSICAL;
        super.className = "Paladin";
        super.experience = 0;
        super.records = new Records(0, 0);
        super.spriteDrawableId = R.drawable.sprite_paladin;
        super.iconDrawableId = R.drawable.icon_paladin;
        super.specialDescriptionId = R.string.paladin_special;
    }
}
