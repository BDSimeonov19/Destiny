package com.example.destiny.data.models.adventurer;

import com.example.destiny.R;
import com.example.destiny.data.models.special.LifeSteal;

public class Warlock extends Adventurer{
    public Warlock(String adventurerName)
    {
        int attack = 7;
        int physicalResistance = 2;
        int magicalResistance = 2;
        int maxHealth = 17;
        int currentHealth = maxHealth;
        float critRate = 0.05f;
        float critDamage = 1.4f;

        super.combatStats = new CombatStatistics(attack, physicalResistance, magicalResistance, maxHealth, currentHealth, critRate, critDamage);

        super.special = new LifeSteal(super.combatStats);

        super.id = java.util.UUID.randomUUID();
        super.adventurerName = adventurerName;
        super.attackType = AttackType.MAGICAL;
        super.className = "Warlock";
        super.experience = 0;
        super.records = new Records(0, 0);
        super.spriteDrawableId = R.drawable.sprite_warlock;
        super.iconDrawableId = R.drawable.icon_warlock;
        super.specialDescriptionId = R.string.warlock_special;
    }
}
