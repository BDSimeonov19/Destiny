package com.example.destiny.domain.adventurers;

import com.example.destiny.R;
import com.example.destiny.domain.Records;

public class Berserker extends Adventurer{
    public Berserker(String adventurerName)
    {
        int attack = 5;
        int physicalResistance = 2;
        int magicalResistance = 1;
        int maxHealth = 15;
        int currentHealth = maxHealth;
        float critRate = 0.05f;
        float critDamage = 1.4f;

        super.combatStats = new CombatStatistics(attack, physicalResistance, magicalResistance, maxHealth, currentHealth, critRate, critDamage);

        super.id = java.util.UUID.randomUUID();
        super.adventurerName = adventurerName;
        super.attackType = AttackType.PHYSICAL;
        super.className = "Berserker";
        super.experience = 0;
        // TODO: add records
        super.records = new Records(0, 0);
        super.specialCooldown = 0;
        super.spriteDrawableId = R.drawable.berserker_sprite;
    }

    public int specialAttack() {
        return 0;
    }
}
