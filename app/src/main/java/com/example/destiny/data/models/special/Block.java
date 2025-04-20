package com.example.destiny.data.models.special;

import com.example.destiny.data.models.adventurer.Adventurer;
import com.example.destiny.data.models.adventurer.CombatStatistics;
import com.example.destiny.data.models.enemy.Enemy;

public class Block extends SpecialEffect
{
    private int enemyAttack = 0;
    public Block(CombatStatistics combatStatistics) {
        super(combatStatistics, "Block");
        // cooldown
        super.maxCooldown = 4;
        super.currentCooldown = 0;

        // duration
        super.maxDuration = 1;
        super.currentDuration = 0;
    }

    @Override
    public void applySpecialEffect(Adventurer adventurer, Enemy enemy)
    {
        // set enemy attack to 0 to "block"
        enemyAttack = enemy.combatStats.attack;
        enemy.combatStats.attack = 0;

        currentDuration = maxDuration;
    }

    @Override
    public void executeSpecialEffect(Adventurer adventurer, Enemy enemy)
    {
        // if special effect is to expire this turn, remove buff
        if(currentDuration == 1)
        {
            enemy.combatStats.attack = enemyAttack;
            enemyAttack = 0;
        }
        // decrement duration by one turn
        currentDuration -= currentDuration > 0 ? 1 : 0;
    }

    @Override
    public String battleLogLine(Enemy enemy) {
        return "";
    }
}
