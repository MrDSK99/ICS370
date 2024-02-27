package com.example.prototype_1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private TextField output;

    @FXML
    private TextField tfProductName;

    @FXML
    void btnSearch(ActionEvent event) {

        //mock inventory set here
        //bad spot, needs to be moved to SearchApplication main()
        Inventory i = new Inventory();
        InventoryHolder currentInventory = InventoryHolder.getInstance();
        currentInventory.setInventory(i);
        currentInventory.getInventory().addItem("apples", 22);
        currentInventory.getInventory().addItem("bananas", 11);
        currentInventory.getInventory().addItem("watermelons", 5);
        currentInventory.getInventory().addItem("oranges", 19);
        currentInventory.getInventory().addItem("mangoes", 9);
        currentInventory.getInventory().addItem("carrots", 33);
        currentInventory.getInventory().addItem("avocados", 14);
        currentInventory.getInventory().addItem("onions", 12);
        currentInventory.getInventory().addItem("potatoes", 25);
        currentInventory.getInventory().addItem("peppers", 3);

        Stage mainWindow = (Stage) tfProductName.getScene().getWindow();

        Product item = new Product(null,-1);
        item.setName(tfProductName.getText().toLowerCase()); //make input case-insensitive
        ProductHolder product = ProductHolder.getInstance();

        product.setProduct(item);
        product.getProduct().setQuantity(currentInventory.getInventory().returnQuantity(product.getProduct().getName()));

        if (product.getProduct().getQuantity() == -1)   //if product doesn't exist in inventory, output "(Product) does not exist in inventory."
            output.setText(product.getProduct().getName().substring(0,1).toUpperCase()+product.getProduct().getName().substring(1)+" does not exist in inventory.");
        else                                            //else output "There are # (product) in stock."
            output.setText("There are "+product.getProduct().getQuantity()+" "+product.getProduct().getName()+" in stock.");
    }
}
