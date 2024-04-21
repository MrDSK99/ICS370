package com.metrosoftwaresolutions.inventory_application;

public class Add_Product_Command implements Command {

    private Product product;
    Inventory inventory = Inventory.getInstance();

    public Add_Product_Command(Product product) {
        this.product = product;
    }

    @Override
    public void execute() {
        inventory.addFromCommand(product);
    }

    @Override
    public void undo() {
        inventory.removeFromCommand(product);
    }
}