package com.metrosoftwaresolutions.inventory_application;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    @Test
    void InitializeInventoryTest() {
        Inventory inventory = Inventory.getInstance();
        assertNotEquals(inventory, null);
    }
    @Test
    void SetInventoryTest() {
        Inventory inventory = Inventory.getInstance();
        inventory.addItem("item 1", 1);
        inventory.addItem("item 2", 2);
        int quantity1 = inventory.getQuantity("item 1");
        int quantity2 = inventory.getQuantity("item 2");
        assertEquals(quantity1, 1);
        assertEquals(quantity2, 2);
    }
    @Test
    void GetAllInventoryTest() {
        Inventory inventory = Inventory.getInstance();
        inventory.addItem("item 1", 1);
        inventory.addItem("item 2", 2);
        inventory.addItem("item 3", 3);
        ArrayList<Product> allProducts = new ArrayList<>();
        allProducts = inventory.getAllInventory();
        assertEquals(allProducts.size(), 3);
        boolean item_1_in_list = false, item_2_in_list = false, item_3_in_list = false;
        for (Product product: allProducts) {
            switch (product.getName()) {
                case "item 1":
                    item_1_in_list = true;
                case "item 2":
                    item_2_in_list = true;
                case "item 3":
                    item_3_in_list = true;
            }
        }
        assertTrue(item_1_in_list);
        assertTrue(item_2_in_list);
        assertTrue(item_3_in_list);
    }
}