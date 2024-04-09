package com.metrosoftwaresolutions.inventory_application;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Delivery_Handler implements Runnable{

    //TODO
    //make sure this runs in its own thread, video on Discord
    //create a queue and fill it with mock Products, some from the mock Inventory Products in Main and some new Products
    //create a loop
    //inside the loop while(true)
    //remove objects from the queue
    //find the existing Product name in inventory and add to the quantity, or add the new Product name and quantity
    //Thread.sleep(3000); at the bottom of the loop for idk 3 or so seconds
    //you can make sure this works by checking the Show All table in the GUI
    //keep JSON persistence in mind for the next time this runs, I wouldn't add massive quantities of stuff
    private Queue<Product> deliveryQueue;
    private Inventory inventory;
    
    public Delivery_Handler() {
        this.deliveryQueue = new ConcurrentLinkedQueue<>();
        deliveryQueue.add(new Product("bananas", 5));
        deliveryQueue.add(new Product("starfruit", 6));
        deliveryQueue.add(new Product("apples", 2));
        this.inventory = Inventory.getInstance();
    }

    public void run() {
        while(true){
            try {
                processDeliveryQueue();
                break;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void processDeliveryQueue() throws InterruptedException {
        while(!deliveryQueue.isEmpty()){
            Thread.sleep(3000);
            Product queueItem = deliveryQueue.poll();

            try {
                boolean success = processDelivery(queueItem);

                if (!success) {
                    System.out.println("Unable to process delivery for " + queueItem.getName());
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            Thread.sleep(3000);
        }
    }

    private boolean processDelivery(Product queueItem) {
        String itemName = queueItem.getName();
        int quantity = queueItem.getQuantity();
        Integer currentQuantity = inventory.getQuantity(itemName);
        if (currentQuantity != null) {
            inventory.updateQuantity(itemName, quantity);
            System.out.println("Delivery successful: " + quantity + " " + itemName + " delivered.");
            System.out.println("New quantity of " + itemName + ": " + (currentQuantity + quantity));
            return true;
        }
        else {
            inventory.addItem(itemName, quantity);
            System.out.println("Delivery successful: " + quantity + " " + itemName + " delivered.");
            System.out.println("New quantity of " + itemName + ": " + quantity);
            return true;
        }
    }
}
