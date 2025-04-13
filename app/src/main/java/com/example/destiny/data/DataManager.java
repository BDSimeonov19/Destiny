package com.example.destiny.data;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

// this class is a singleton
public class DataManager extends Service {
    private static final DataManager instance = new DataManager();

    // private constructor as to forbid creating instances
    private DataManager() {}

    public static DataManager getInstance() {
        return instance;
    }

    public void write(String data, String fileName)
    {
        try
        {
            FileOutputStream outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(data.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String read(String fileName)
    {
        String data = "";
        try
        {
            FileInputStream fileInputStream = openFileInput(fileName);
            int character;

            while( (character = fileInputStream.read()) != -1){
                data = data + Character.toString((char)character);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return data;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
