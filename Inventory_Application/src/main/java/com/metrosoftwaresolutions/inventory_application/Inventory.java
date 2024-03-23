package com.metrosoftwaresolutions.inventory_application;

import java.util.*;

public class Inventory {

    private static Inventory newInventory;
    private Map<String, Integer> inventory = new HashMap<String, Integer>();

    private Inventory() {}                          //This makes it so
                                                    //only one Inventory object
    public static Inventory getInstance() {         //can be created
        if (newInventory == null)                   //simulating a database.
            newInventory = new Inventory();
        return newInventory;
    }

    public void addItem(String itemName, Integer quantity) {
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
        if (!this.inventory.containsKey(itemName))
            return -1;
        else
            return this.inventory.get(itemName);
    }
    public ArrayList<Product> getAllInventory() {
        ArrayList<Product> allInventory = new ArrayList<Product>();
        Iterator<String> it = inventory.keySet().iterator();
        while(it.hasNext())   {
            Product product = new Product(null, -1);
            String key = it.next();
            product.setName(key);
            product.setQuantity(inventory.get(key));
            allInventory.add(product);
        }
        return allInventory;
    }
}

