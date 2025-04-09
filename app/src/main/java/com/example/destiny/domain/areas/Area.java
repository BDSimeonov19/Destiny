package com.example.destiny.domain.areas;

import com.example.destiny.domain.adventurers.Adventurer;

import java.util.HashMap;
import java.util.UUID;

public abstract class Area {
    public HashMap<UUID, Adventurer> adventurers = new HashMap<>();

    public void addAdventurer(Adventurer adventurer)
    {
        this.adventurers.put(adventurer.id, adventurer);
    }
    public void removeAdventurer(UUID id) {
        this.adventurers.remove(id);
    }
    public boolean containsAdventurer(UUID id) {
        return this.adventurers.containsKey(id);
    }
    public Adventurer getAdventurerById(UUID id) {
        return this.adventurers.get(id);
    }
    public abstract void listAdventurers();

    public static void moveAdventurer(UUID id, Area currentLocation, Area newLocation)
    {
        Adventurer adventurer = null;

        // take adventurer from current location and move to new location
        adventurer = currentLocation.getAdventurerById(id);
        currentLocation.removeAdventurer(id);
        newLocation.addAdventurer(adventurer);

    }
}
