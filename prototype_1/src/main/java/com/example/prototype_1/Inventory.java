package com.example.prototype_1;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private Map<String, Integer> inventory = new HashMap<String, Integer>();

    public Inventory() {}

    public void addItem(String itemName, Integer quantity) {
        this.inventory.put(itemName, quantity);
    }

    public Integer returnQuantity(String itemName) {
        if (!this.inventory.containsKey(itemName))
            return -1;
        else
            return this.inventory.get(itemName);
    }
}
