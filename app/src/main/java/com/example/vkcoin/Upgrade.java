package com.example.vkcoin;

import com.example.vkcoin.repository.UpgradeRepository;
import com.example.vkcoin.ui.MainActivity;

public class Upgrade {
    MainActivity.UpgradeType type;
    private String name;
    private float price;
    private float gain;

    public Upgrade(MainActivity.UpgradeType type, String name, float price, float gain) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.gain = gain;
    }


    public MainActivity.UpgradeType getType() {
        return type;
    }

    public float getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public float getGain() {
        return gain;
    }

}
