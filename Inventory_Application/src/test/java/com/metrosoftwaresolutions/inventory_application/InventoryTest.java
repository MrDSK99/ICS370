package com.metrosoftwaresolutions.inventory_application;

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
        int quantity1 = inventory.getQuantity("item 1");
        int quantity2 = inventory.getQuantity("item 2");
        assertEquals(quantity1, 1);
        assertEquals(quantity2, 2);
    }
    @Test
    void TestPersistence() {
        Inventory inventory = Inventory.getInstance();
        String product_name = "apples";
        int inventory_level = inventory.getQuantity(product_name);
        assertEquals(inventory_level, 22);
    }
}