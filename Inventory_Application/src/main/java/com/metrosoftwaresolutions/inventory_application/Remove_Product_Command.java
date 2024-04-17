package com.metrosoftwaresolutions.inventory_application;

public class Remove_Product_Command implements Command {

    Inventory inventory = Inventory.getInstance();

    private Product product;

    public Remove_Product_Command(Product product) {
        this.product = product;
    }

    @Override
    public void execute() {
    //TODO
    }
}