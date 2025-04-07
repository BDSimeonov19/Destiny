package com.example.destiny.domain;

// this class is a singleton
public class Guild extends Area {
    private static final Guild instance = new Guild();

    // private constructor as to forbid creating instances
    private Guild(){}

    public static Guild getInstance() {
        return instance;
    }

    public void listAdventurers() {

    }
}
