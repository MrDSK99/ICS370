package com.metrosoftwaresolutions.inventory_application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void ProductTestSetAndGet() {
        Product product = new Product(null, null);
        product.setName("hamburgers");
        product.setQuantity(40);
        String product_name = product.getName();
        Integer quantity = product.getQuantity();
        Assertions.assertEquals("hamburgers", product_name);
        Assertions.assertEquals(40, quantity);
    }

}