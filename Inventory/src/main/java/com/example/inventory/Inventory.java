package com.example.inventory;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private static Inventory newInventory;

    private Map<String, Integer> inventory = new HashMap<String, Integer>();
    // Initialize inventory with some items

    private Inventory() {}

    public static Inventory getInstance() {
        if (newInventory == null)
            newInventory = new Inventory();
        return newInventory;
    }

    public void addItem(String itemName, int quantity) {
        this.inventory.put(itemName, quantity);
    }

    public void updateQuantity(String itemName, Integer additionalQuantity) {
        if (this.inventory.containsKey(itemName)) {
            Integer currentQuantity = this.inventory.get(itemName);
            this.inventory.put(itemName, currentQuantity + additionalQuantity);
        } else {
            this.inventory.put(itemName, additionalQuantity);
        }
    }

    public Integer returnQuantity(String itemName) {
        return inventory.getOrDefault(itemName, -1);
    }

}
