package com.example.prototype_1;

public class InventoryHolder {

    private Inventory inventory;
    private static InventoryHolder INSTANCE = new InventoryHolder();

    public InventoryHolder() {}

    public static InventoryHolder getInstance() {
        return INSTANCE;
    }

    public void setInventory(Inventory i) {
        this.inventory = i;
    }

    public Inventory getInventory() {
        return this.inventory;
    }
}

//this should allow Controller and SearchApplication to share the Inventory, but not set up correctly
