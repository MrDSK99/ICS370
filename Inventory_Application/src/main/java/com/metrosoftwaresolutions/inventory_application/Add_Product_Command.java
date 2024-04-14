package com.metrosoftwaresolutions.inventory_application;

public class Add_Product_Command implements Command {

    Inventory inventory = Inventory.getInstance();

    private Product product;

    public Add_Product_Command(Product product) {
        this.product = product;
    }

    @Override
    public void execute() {
        if (inventory.getQuantity(product.getName()) == -1)
            inventory.addItem(product);
        else  // If the item already exists, update its quantity
            inventory.addQuantity(product);
    }

}
