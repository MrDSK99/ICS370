package com.metrosoftwaresolutions.inventory_application;

public class Remove_Product_Command implements Command {

    private Product product;
    Inventory inventory = Inventory.getInstance();

    public Remove_Product_Command(Product product) {
        this.product = product;
    }

    @Override
    public void execute() {
        inventory.removeFromCommand(product);
    }
    @Override
    public void undo() {
        inventory.addFromCommand(product);
    }
}