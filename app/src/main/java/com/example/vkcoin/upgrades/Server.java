package com.example.vkcoin.upgrades;

public class Server extends Upgrade{
    private String name = "server";

    private float gain = 0.1f;
    private float price;
    private float quantity;



    public String getName() {
        return name;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getGain() {
        return gain;
    }

    public void setGain(float gain) {
        this.gain = gain;
    }
}
