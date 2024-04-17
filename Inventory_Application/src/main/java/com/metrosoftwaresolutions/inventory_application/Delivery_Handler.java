package com.metrosoftwaresolutions.inventory_application;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Delivery_Handler implements Runnable {

    private Queue<Product> deliveryQueue;
    private Inventory inventory;
    
    public Delivery_Handler() {
        this.deliveryQueue = new ConcurrentLinkedQueue<>();
        deliveryQueue.add(new Product("bananas", 8));
        deliveryQueue.add(new Product("starfruit", 6));
        deliveryQueue.add(new Product("apples", 10));
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
            Thread.sleep(2000);
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
        if (currentQuantity != -1) {
            inventory.addQuantity(queueItem);
            System.out.println("Delivery successful: " + quantity + " " + itemName + " delivered.");
            System.out.println("New quantity of " + itemName + ": " + (currentQuantity + quantity));
            return true;
        }
        else {
            inventory.addItem(queueItem);
            System.out.println("Delivery successful: " + quantity + " " + itemName + " delivered.");
            System.out.println("New quantity of " + itemName + ": " + quantity);
            return true;
        }
    }
}