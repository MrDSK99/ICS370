package com.metrosoftwaresolutions.inventory_application;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Purchase_Handler implements  Runnable{

    //TODO
    //make sure this runs in its own thread, video on Discord
    //create a queue and fill it with some mock Products, they should come from the initial mock Inventory Products from Main
    //create a loop
    //inside the loop while(true)
    //remove objects from the queue
    //find the existing Product name in inventory and subtract from to the quantity
    //Thread.sleep(3000); at the bottom of the loop for idk 3 or so seconds
    //you can make sure this works by checking the Show All table in the GUI
    //make sure the inventory level will not drop below 0, don't have to code this yet just be smart about what subtracting
    //keep JSON persistence in mind for the next time this runs, don't subtract below 0 in the future (subtract small amounts from large quantities)
    private Queue<Product> purchaseQueue;
    private Inventory inventory;

   public void PurchaseHandler() {
        this.purchaseQueue = new ConcurrentLinkedQueue<>();
        this.inventory = Inventory.getInstance();
    }

    @Override
    public void run() {
        while (true) {
            processPurchaseQueue();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    private void processPurchaseQueue() {
        while (!purchaseQueue.isEmpty()) {
            Product product = purchaseQueue.poll();
            try {
                boolean success = processPurchase(product); // Process the purchase

                if (!success) {
                    System.out.println("Checkout failed: Unable to process purchase for " + product.getName());
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private boolean processPurchase(Product product) {
        String itemName = product.getName();
        int quantity = product.getQuantity();

        // Check if item exists in inventory
        if (inventory.getQuantity(itemName) != -1) {
            int currentQuantity = inventory.getQuantity(itemName);
            // Check if there's sufficient quantity in inventory for the purchase
            if (currentQuantity >= quantity) {
                inventory.updateQuantity(itemName, -quantity);
                System.out.println("Checkout successful: " + quantity + " " + itemName + " purchased.");
                System.out.println("New quantity of " + itemName + ": " + (currentQuantity - quantity));
                return true;
            } else {
                System.out.println("Checkout failed: Insufficient quantity of " + itemName + " in stock.");
            }
        } else {
            System.out.println("Checkout failed: Item " + itemName + " not found in inventory.");
        }
        return false;
    }

    // Method to add a purchase to the queue
    public void addToQueue(Product product) {
        purchaseQueue.add(product);
    }

}
