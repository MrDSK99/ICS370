package com.metrosoftwaresolutions.inventory_application;

import java.io.File;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Inventory {

    private static Inventory newInventory;
    private List<Product> products;
    private ObjectMapper mapper;
    private static final String JSON_FILE = "inventory.json";  //Path to the json file


    private Inventory() {
        products = new ArrayList<>();  // Initialize the list of products
        mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        loadInventory();
    }


    public static Inventory getInstance() {
        if (newInventory == null)
            newInventory = new Inventory();
        return newInventory;
    }

    public void addItem(String itemName, Integer quantity) {
        products.add(new Product(itemName, quantity));
        saveInventory();
    }

    public void updateQuantity(String itemName, Integer additionalQuantity) {
        for (Product product : products)
            if (product.getName().equals(itemName)) {
                int currentQuantity = product.getQuantity();
                product.setQuantity(currentQuantity + additionalQuantity);
                saveInventory();
                return;
            } else

        addItem(itemName, additionalQuantity);
        saveInventory();
    }


    // Get quantity of a product
    public Integer getQuantity(String itemName) {
        for (Product product : products) {
            if (product.getName().equals(itemName)) {
                return product.getQuantity();
            }
        }
        return -1;
    }

    // Get all products in inventory
    public List<Product> getAllInventory() {
        return products;
    }

    // Save inventory to JSON file
    public void saveInventory() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Serialize inventory data to JSON format
            String jsonInventory = objectMapper.writeValueAsString(products);
            Files.write(Paths.get(JSON_FILE),jsonInventory.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving inventory: " + e.getMessage());
        }
    }


    public Integer returnQuantity(String itemName) {
        for (Product product : products) {
            if (product.getName().equals(itemName)) {
                return product.getQuantity();
            }
        }
        return -1; // Item not found in inventory
    }





    // Load inventory from JSON file
    void loadInventory() {
        try {
            File file = new File(JSON_FILE);
            if (file.exists()) {
                CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, Product.class);
                products = mapper.readValue(file, new TypeReference<List<Product>>() {
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

