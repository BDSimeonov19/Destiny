package com.example.destiny.data.models.adventurer;

import java.io.Serializable;

public class CombatStatistics implements Serializable {
    public CombatStatistics(int attack, int physicalResistance, int magicalResistance, int maxHealth, int currentHealth, float critRate, float critDamage)
    {
        this.attack = attack;
        this.physicalResistance = physicalResistance;
        this.magicalResistance = magicalResistance;
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.critRate = critRate;
        this.critDamage = critDamage;
    }
    public int attack;
    public int physicalResistance;
    public int magicalResistance;
    public int maxHealth;
    public int currentHealth;
    public float critRate;
    public float critDamage;
}
