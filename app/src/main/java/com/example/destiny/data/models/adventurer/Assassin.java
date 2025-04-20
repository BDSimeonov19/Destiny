package com.example.destiny.data.models.adventurer;

import com.example.destiny.R;
import com.example.destiny.data.models.Records;
import com.example.destiny.data.models.special.Bleed;

public class Assassin extends Adventurer{
    public Assassin(String adventurerName)
    {
        int attack = 5;
        int physicalResistance = 2;
        int magicalResistance = 3;
        int maxHealth = 17;
        int currentHealth = maxHealth;
        float critRate = 0.2f;
        float critDamage = 1.4f;

        super.combatStats = new CombatStatistics(attack, physicalResistance, magicalResistance, maxHealth, currentHealth, critRate, critDamage);

        super.special = new Bleed(super.combatStats);

        super.id = java.util.UUID.randomUUID();
        super.adventurerName = adventurerName;
        super.attackType = AttackType.PHYSICAL;
        super.className = "Assassin";
        super.experience = 0;
        super.records = new Records(0, 0);
        super.spriteDrawableId = R.drawable.sprite_assassin;
        super.iconDrawableId = R.drawable.icon_assassin;
        super.specialDescriptionId = R.string.assassin_special;
    }

    public int specialAttack() {
        return 0;
    }
}
