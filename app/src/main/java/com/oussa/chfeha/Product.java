package com.oussa.chfeha;


public class Product {
    private String[] names = {"fr", "en"};
    private int water;

    public Product(String[] names, int water) {
        this.names = names;
        this.water = water;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }
}
