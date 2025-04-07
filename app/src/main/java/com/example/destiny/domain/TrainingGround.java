package com.example.destiny.domain;

// this class is a singleton
public class TrainingGround extends Area{

    private static final TrainingGround instance = new TrainingGround();

    // private constructor as to forbid creating instances
    private TrainingGround(){}

    public static TrainingGround getInstance() {
        return instance;
    }

    public void train()
    {

    }

    public void listAdventurers() {

    }
}
