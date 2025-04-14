package com.example.destiny.domain.area;

import com.example.destiny.data.AdventurerRepository;
import com.example.destiny.data.models.adventurer.Adventurer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public abstract class Area {
    public HashMap<UUID, Adventurer> adventurers = new HashMap<>();
    public String fileName;
    AdventurerRepository repository = new AdventurerRepository();

    public void addAdventurer(Adventurer adventurer)
    {
        adventurers.put(adventurer.id, adventurer);
        repository.saveAdventurers(adventurers, fileName);
    }
    public void removeAdventurer(UUID id) {
        adventurers.remove(id);
        repository.saveAdventurers(adventurers, fileName);
    }
    public boolean containsAdventurer(UUID id) {
        return adventurers.containsKey(id);
    }
    public Adventurer getAdventurerById(UUID id) {
        return adventurers.get(id);
    }
    public ArrayList<Adventurer> getAllAdventurers()
    {
        return new ArrayList<>(adventurers.values());
    }

    public void fetchData()
    {
        adventurers = repository.getAdventurers(fileName);
    }


    public static void moveAdventurer(UUID id, Area currentLocation, Area newLocation)
    {
        Adventurer adventurer = null;

        // take adventurer from current location and move to new location
        adventurer = currentLocation.getAdventurerById(id);
        currentLocation.removeAdventurer(id);
        newLocation.addAdventurer(adventurer);

    }
}
