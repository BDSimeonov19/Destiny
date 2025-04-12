package com.example.destiny.domain.area;

import com.example.destiny.domain.BattleManager;
import com.example.destiny.domain.adventurer.Adventurer;

import java.util.ArrayList;

// this class is a singleton
public class BattleArena extends Area{

    private static final BattleArena instance = new BattleArena();

    private BattleManager battleManager;

    // private constructor as to forbid creating instances
    private BattleArena(){}

    public static BattleArena getInstance() {
        return instance;
    }

    public void battle()
    {
        // don't start battle if there isn't exactly one adventurer in the area
        if(super.adventurers.size() != 1)
        {
            return;
        }

        ArrayList<Adventurer> adventurersList = new ArrayList<>(super.adventurers.values());
        // TODO: add enemy random creation, add activity for battle, check if the live data works
        // TODO: rethink how the battlearena area handles the logic here :D
        // TODO: basically tomorrow focus on the activity and hooking it up to a visual
        // TODO: also don't forget that you need to handle EXP and saving good luck!
        //this.battleManager = new BattleManager(adventurersList.get(0), );

    }
    public void listAdventurers() {

    }
}
