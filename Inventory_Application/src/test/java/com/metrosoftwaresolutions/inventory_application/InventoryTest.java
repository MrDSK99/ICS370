package com.metrosoftwaresolutions.inventory_application;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    @Test
    void InitializeInventoryTest() {
        Inventory inventory = Inventory.getInstance();
        assertNotEquals(null, inventory);
    }
    @Test
    void SetInventoryTest() { //remove these from json file after running tests
        Inventory inventory = Inventory.getInstance();
        inventory.addItem("item 1", 1);
        inventory.addItem("item 2", 2);
        int quantity1 = inventory.getQuantity("item 1");
        int quantity2 = inventory.getQuantity("item 2");
        assertEquals(1, quantity1);
        assertEquals(2, quantity2);
    }
    @Test
    void TestPersistence() { //must run SetInventoryTest() first for this to pass
        Inventory inventory = Inventory.getInstance();
        String product_name = "item 1";
        int inventory_level = inventory.getQuantity(product_name);
        assertEquals(1, inventory_level);
        product_name = "item 2";
        inventory_level = inventory.getQuantity(product_name);
        assertEquals(2, inventory_level);
    }
}