package com.example.destiny.data;

import android.content.Context;

import com.example.destiny.app.App;
import com.example.destiny.data.models.adventurer.Adventurer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AdventurerRepository {
    private static final AdventurerRepository instance = new AdventurerRepository();

    public static AdventurerRepository getInstance() {
        return instance;
    }

    public void saveAdventurers(ArrayList<Adventurer> adventurers, String fileName)
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

    public ArrayList<Adventurer> getAdventurers(String fileName)
    {
        ArrayList<Adventurer> adventurers = new ArrayList<>();
        try
        {
            // read adventurers from file
            FileInputStream fileInputStream = App.getInstance().getContext().openFileInput(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            adventurers = (ArrayList<Adventurer>) objectInputStream.readObject();
            objectInputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return adventurers;
    }
}
