package com.example.destiny.data.models.special;

import java.io.Serializable;

public abstract class Special implements Serializable {
    public int maxCooldown;
    public int currentCooldown;
    public String name;
}
