package com.example.destiny.domain.area;

import com.example.destiny.data.adventurer.Adventurer;
import com.example.destiny.data.adventurer.Assassin;
import com.example.destiny.data.adventurer.Berserker;
import com.example.destiny.data.adventurer.Knight;
import com.example.destiny.data.adventurer.Mage;
import com.example.destiny.data.adventurer.Paladin;
import com.example.destiny.data.adventurer.Warlock;

import java.util.ArrayList;

// this class is a singleton
public class Guild extends Area {
    private static final Guild instance = new Guild();

    // private constructor as to forbid creating instances
    private Guild(){}

    public static Guild getInstance() {
        return instance;
    }

    public void createAdventurer(String adventurerName, String className)
    {
        Adventurer adventurer;

        switch (className) {
            case "Knight":
                adventurer = new Knight(adventurerName);
                break;
            case "Paladin":
                adventurer = new Paladin(adventurerName);
                break;
            case "Assassin":
                adventurer = new Assassin(adventurerName);
                break;
            case "Warlock":
                adventurer = new Warlock(adventurerName);
                break;
            case "Mage":
                adventurer = new Mage(adventurerName);
                break;
            case "Berserker":
                adventurer = new Berserker(adventurerName);
                break;
            default:
                adventurer = null;
                break;
        }

        if(adventurer != null)
        {
            this.addAdventurer(adventurer);
        }
    }

    public ArrayList<Adventurer> getAllAdventurers()
    {
        return new ArrayList<Adventurer>(this.adventurers.values());
    }
}
