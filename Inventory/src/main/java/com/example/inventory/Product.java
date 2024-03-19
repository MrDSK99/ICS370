package com.example.inventory;

public class Product {
    private static final String InventoryManager = null;
    public String itemName;
    public int quantity;

    public Product(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;

    }

    public static void updateQuantity(String itemName, int AdditionalQty) {
        if (Product.InventoryManager.contains(itemName))
            ;

    }

    public String getItemName() {
        return this.itemName;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
