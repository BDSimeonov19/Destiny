package com.example.destiny.data;

import java.io.Serializable;

public class Records implements Serializable {
    public Records(int battles, int victories)
    {
        this.battles = battles;
        this.victories = victories;
    }
    public int battles;
    public int victories;
}
