package com.example.destiny.domain;

// this class is a singleton
public class BattleArena extends Area{

    private static final BattleArena instance = new BattleArena();

    // private constructor as to forbid creating instances
    private BattleArena(){}

    public static BattleArena getInstance() {
        return instance;
    }

    public void fight()
    {

    }
    public void listAdventurers() {

    }
}
