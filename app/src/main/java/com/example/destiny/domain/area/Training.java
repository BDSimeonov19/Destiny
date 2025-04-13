package com.example.destiny.domain.area;

import java.io.File;

// this class is a singleton
public class Training extends Area{

    private static final Training instance = new Training();

    // private constructor as to forbid creating instances
    private Training()
    {
        super.fileName = "training.json";
    }

    public static Training getInstance() {
        return instance;
    }

    public void train()
    {

    }
}
