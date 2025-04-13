package com.example.destiny.domain.area;

import android.content.Context;

import com.example.destiny.app.App;
import com.example.destiny.data.adventurer.Adventurer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public abstract class Area {
    public HashMap<UUID, Adventurer> adventurers = new HashMap<>();
    public String fileName;

    public void addAdventurer(Adventurer adventurer)
    {
        adventurers.put(adventurer.id, adventurer);
        saveChanges();
    }
    public void removeAdventurer(UUID id) {
        adventurers.remove(id);
        saveChanges();
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
        try
        {
            // read adventurers from file
            FileInputStream fileInputStream = App.getInstance().getContext().openFileInput(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            HashMap<UUID, Adventurer> adventurers = (HashMap<UUID, Adventurer>) objectInputStream.readObject();
            objectInputStream.close();
            this.adventurers = adventurers;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveChanges()
    {
        try
        {
            // save adventurers to file
            FileOutputStream fileOutputStream = App.getInstance().getContext().openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(adventurers);
            objectOutputStream.flush();
            objectOutputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
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
