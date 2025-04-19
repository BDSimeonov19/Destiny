package com.example.destiny.data.models.special;

public abstract class SpecialEffect extends Special{
    public SpecialEffect(int maxCooldown, int currentCooldown) {
        super.maxCooldown = maxCooldown;
        super.currentCooldown = currentCooldown;
    }
}
