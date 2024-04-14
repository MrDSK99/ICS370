package com.metrosoftwaresolutions.inventory_application;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Purchase_Handler implements Runnable {

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

   public Purchase_Handler() {
       this.purchaseQueue = new ConcurrentLinkedQueue<>();
       //mock Products set here
       purchaseQueue.add(new Product("apples", 2));
       purchaseQueue.add(new Product("peaches", 3));
       purchaseQueue.add(new Product("pineapples", 2));
       this.inventory = Inventory.getInstance();
    }

    @Override
    public void run()  {
        while (true) {
            try {
                processPurchaseQueue();
                break;                   //close the thread when done processing queue, for demo purposes
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void processPurchaseQueue() throws InterruptedException {
        while (!purchaseQueue.isEmpty()) {
            Thread.sleep(3000);     //sleep here to create simulated lag between purchases
            Product product = purchaseQueue.poll();
            try {
                boolean success = processPurchase(product); // Process the purchase

                if (!success) {
                    System.out.println("Unable to process purchase for " + product.getName());
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            Thread.sleep(3000);     //split to create lag for first purchase
        }
    }

    private boolean processPurchase(Product product) {
        String itemName = product.getName();
        int quantity = product.getQuantity();

        // Check if item exists in inventory
        Integer currentQuantity = inventory.getQuantity(itemName);
        if (currentQuantity != null && currentQuantity > 0) {
            if (currentQuantity >= quantity) {
                inventory.removeQuantity(product);
                System.out.println("Checkout successful: " + quantity + " " + itemName + " purchased.");
                System.out.println("New quantity of " + itemName + ": " + (currentQuantity - quantity));
                return true;
            }
        } else {
            System.out.println("Checkout failed: Insufficient quantity  " + itemName + " in stock.");
        }
        return false;
    }
}