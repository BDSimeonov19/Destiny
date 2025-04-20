package com.example.destiny.domain.area;

import com.example.destiny.data.AdventurerRepository;
import com.example.destiny.data.models.adventurer.Adventurer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Area {
    protected ArrayList<Adventurer> adventurers = new ArrayList<>();
    protected String fileName;
    private final AdventurerRepository repository = new AdventurerRepository();

    public void addAdventurer(Adventurer adventurer)
    {
        adventurers.add(adventurer);
        repository.saveAdventurers(adventurers, fileName);
    }
    public void removeAdventurer(UUID id) {
        adventurers.removeIf(x -> x.id.equals(id));
        repository.saveAdventurers(adventurers, fileName);
    }
    public Adventurer getAdventurerById(UUID id) {
        return adventurers.stream().filter(x -> x.id.equals(id)).findFirst().orElse(null);
    }
    public ArrayList<Adventurer> getAllAdventurers()
    {
        return adventurers;
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
