package com.metrosoftwaresolutions.inventory_application;

public class Add_Product_Command implements Command {

    private Product product;

    public Add_Product_Command(Product product) {
        this.product = product;
    }

    @Override
    public void execute() {
        Inventory inventory = Inventory.getInstance();
        inventory.addFromCommand(product);
    }
}