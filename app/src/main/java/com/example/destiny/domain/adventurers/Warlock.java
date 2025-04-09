package com.example.destiny.domain.adventurers;

import com.example.destiny.domain.Records;

public class Warlock extends Adventurer{
    public Warlock(String adventurerName)
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
        super.attackType = AttackType.MAGICAL;
        super.className = "Warlock";
        super.experience = 0;
        // TODO: add records
        super.records = new Records();
        super.specialCooldown = 0;
    }

    public int specialAttack() {
        return 0;
    }
}
