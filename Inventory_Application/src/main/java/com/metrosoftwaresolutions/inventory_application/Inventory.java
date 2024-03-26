package com.metrosoftwaresolutions.inventory_application;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.io.IOException;

public class Inventory {

    private static Inventory inventory;

    private List<Product> products;   // Instance variable to hold list of products
    private ObjectMapper mapper;
    private final String JSON_FILE = "inventory.json";  //Path to the json file
   

   private Inventory() {
        products = new ArrayList<>();
        mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
       loadInventory();
    }                        
                                                    
    public static Inventory getInstance() {         
        if (inventory == null)
            inventory = new Inventory();
        return inventory;
    }

    public void addItem(String itemName, Integer quantity) {
        products.add(new Product(itemName, quantity));
        saveInventory();
    }

    public void updateQuantity(String itemName, Integer additionalQuantity) {
        for (Product product : products) {
            if (product.getName().equals(itemName)) {
                int currentQuantity = product.getQuantity();
                product.setQuantity(currentQuantity + additionalQuantity);
                saveInventory();
                return;
            }
        }
        // If item doesn't exist, add it
        addItem(itemName, additionalQuantity);
    }

    public Integer getQuantity(String itemName) {
         for (Product product : products) {
            if (product.getName().equals(itemName)) {
                return product.getQuantity();
            }
        }
        return -1;
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

     // Get all products in inventory
    public List<Product> getAllInventory() {
        return products;
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
    public void loadInventory() {
        try {
            File file = new File(JSON_FILE);
            if (file.exists()) {
                CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, Product.class);
                products = mapper.readValue(file, new TypeReference<List<Product>>()  {
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   
}

