package com.metrosoftwaresolutions.inventory_application;

import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.io.IOException;
import java.util.*;

public class Inventory {

    private static Inventory newInventory;

    private List<Product> products;   // Instance variable to hold list of products
    private ObjectMapper mapper;
    private final String JSON_FILE = "//Inventory_Application/inventory.json";  //Path to the json file
   

   private Inventory() {
        products = new ArrayList<>();
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
    private void saveInventory() {
        try {
            mapper.writeValue(new File(JSON_FILE), products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     // Get all products in inventory
    public List<Product> getAllInventory() {
        return products;
    }
    
    public ArrayList<Product> getAllInventory() {
        ArrayList<Product> allInventory = new ArrayList<Product>();
        Iterator<String> it = inventory.keySet().iterator();
        while (it.hasNext()) {
            Product product = new Product(null, -1);
            String key = it.next();
            product.setName(key);
            product.setQuantity(inventory.get(key));
            allInventory.add(product);
        }
        return allInventory;
    }

     // Load inventory from JSON file
    private void loadInventory() {
        try {
            File file = new File(JSON_FILE);
            if (file.exists()) {
                CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, Product.class);
                products = mapper.readValue(file, collectionType);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

