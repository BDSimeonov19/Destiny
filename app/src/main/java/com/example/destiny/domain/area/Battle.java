package com.example.destiny.domain.area;

import com.example.destiny.data.models.enemy.Enemy;
import com.example.destiny.data.models.enemy.Flower;
import com.example.destiny.data.models.enemy.Harpy;
import com.example.destiny.data.models.enemy.Mushroom;
import com.example.destiny.data.models.enemy.Skeleton;
import com.example.destiny.data.models.adventurer.Adventurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

// this class is a singleton
public class Battle extends Area{

    private static final Battle instance = new Battle();

    public ArrayList<Enemy> enemies = new ArrayList<>();
    public ArrayList<Class<?>> enemyClassList = new ArrayList<>(Arrays.asList(Flower.class, Mushroom.class, Skeleton.class, Harpy.class));

    // private constructor as to forbid creating instances
    private Battle()
    {
        super.fileName = "battle.json";
    }

    public static Battle getInstance() {
        return instance;
    }

    public void createEnemy(int numberOfEnemies)
    {
        // try-catch block exists to handle a possible instantiation exception
        try {
            Random random = new Random();
            for(int i = 0; i < numberOfEnemies; i++)
            {
                // create instance of randomly selected enemy
                Enemy enemy = (Enemy) enemyClassList.get(random.nextInt(enemyClassList.size())).newInstance();
                enemies.add(enemy);
            }
        }
        catch(Exception e) {
            // do nothing
        }
    }

    // TODO: also don't forget that you need to handle EXP
    public void battleSetup(String difficulty)
    {
        // more than one character means an error has occurred, send everyone other than the last added adventurer to guild
        if(super.adventurers.size() > 1)
        {
            for(int i = 0; i < super.adventurers.size()-1; i++)
            {
                Adventurer adv = super.adventurers.get(i);
                Guild.moveAdventurer(adv.id, this, Guild.getInstance());
            }
        }
        // if is empty, different error has occurred, return
        if(super.adventurers.isEmpty())
        {
            return;
        }

        // extract only adventurer
        Adventurer adventurer = super.adventurers.get(0);

        // heal adventurer
        adventurer.combatStats.currentHealth = adventurer.combatStats.maxHealth;

        // create number of enemies based on difficulty
        switch(difficulty)
        {
            case "Easy":
                createEnemy(1);
                break;
            case "Medium":
                createEnemy(2);
                break;
            case "Hard":
                createEnemy(3);
                break;
        }

    }
}
