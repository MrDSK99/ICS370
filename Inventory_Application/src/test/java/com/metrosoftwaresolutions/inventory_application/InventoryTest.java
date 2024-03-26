package com.metrosoftwaresolutions.inventory_application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        int quantity1 = inventory.returnQuantity("item 1");
        int quantity2 = inventory.returnQuantity("item 2");
        assertEquals(quantity1, 1);
        assertEquals(quantity2, 2);
    }
    @Test
    void GetAllInventoryTest() {
        Inventory inventory = Inventory.getInstance();
        inventory.addItem("item 1", 1);
        inventory.addItem("item 2", 2);
        inventory.addItem("item 3", 3);
        List<Product> allProducts = new ArrayList<>();
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

    @Test
    void getInstance() {
    }

    @Test
    void addItem() {
    }

    @Test
    void updateQuantity() {
    }

    @Test
    void getQuantity() {
    }

    @Test
    void getAllInventory() {
    }

    @Test
    void saveInventory() {
    }

    @Test
    void returnQuantity() {
    }

    @Test
    void loadInventory() {
    }
}