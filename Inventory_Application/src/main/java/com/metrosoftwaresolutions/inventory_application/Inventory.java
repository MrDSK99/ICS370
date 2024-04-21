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

    public void addItem(Product product) {
        products.add(product);
        saveInventory();
    }

    public void addQuantity(Product product) {
       String name = product.getName();
       Integer additional_quantity = product.getQuantity();
        for (Product p : products) {
            if (p.getName().equals(name)) {
                int current_quantity = p.getQuantity();
                p.setQuantity(current_quantity + additional_quantity);
                saveInventory();
                return;
            }
        }
    }

    public void addFromCommand(Product product) {
        if (inventory.getQuantity(product.getName()) == -1)
            inventory.addItem(product);
        else  // If the item already exists, update its quantity
            inventory.addQuantity(product);
    }

    public void removeQuantity(Product product) {
        String name = product.getName();
        Integer quantity_to_remove = product.getQuantity();
        for (Product p : products) {
            if (p.getName().equals(name)) {
                int current_quantity = p.getQuantity();
                p.setQuantity(current_quantity - quantity_to_remove);
                saveInventory();
                return;
            }
        }
    }

    public void removeFromCommand(Product product) {
        inventory.removeQuantity(product);
    }

    public void deleteItem(Product product) {
        String name = product.getName();
        for (Product p : products) {
            if (p.getName().equals(name)) {
                products.remove(p);
                break;
            }
        }
    }

    public Integer getQuantity(String itemName) {
         for (Product product : products) {
            if (product.getName().equals(itemName)) {
                return product.getQuantity();
            }
        }
        return -1;
    }

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