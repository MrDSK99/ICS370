package com.metrosoftwaresolutions.inventory_application;
import java.util.Queue;


public class Purchase_Handler implements Runnable {
        private Inventory inventory;
        private Queue<Product> productQueue;

        public void PurchaseHandler(Inventory inventory, Queue<Product> productQueue) {
            this.inventory = inventory;
            this.productQueue = productQueue;
        }

        @Override
        public void run() {
            while (true) {
                // Remove products from the queue
                Product product = productQueue.poll();
                if (product != null) {
                    // Find the existing product in inventory and subtract from its quantity
                    int existingQuantity = inventory.getQuantity(product.getName());
                    if (existingQuantity != -1) {
                        // Ensure inventory level does not drop below 0
                        int newQuantity = Math.max(existingQuantity - product.getQuantity(), 0);
                        inventory.updateQuantity(product.getName(), newQuantity);
                    }
                }

                // Save updated inventory data to JSON file
                inventory.saveInventory();

                try {
                    // Sleep for 3 seconds
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


