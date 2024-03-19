package com.example.inventory;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class Controller{


    @FXML
    private TextField AddProductName;

    @FXML
    private TextField AddQty;

    @FXML
    private TextField output;

    @FXML
    void btnAddItem(ActionEvent event) {
        String itemName = AddProductName.getText().toLowerCase();
        int quantity = Integer.parseInt(AddQty.getText());

        Inventory inventory = Inventory.getInstance();
        if (inventory.returnQuantity(itemName) == -1){
            inventory.addItem(itemName, quantity);
            output.setText("Item: " + itemName + ", Quantity: " + quantity + " added to inventory.");
        } else { // If the item already exists, update its quantity
            inventory.updateQuantity(itemName,quantity);
            int updatedQuantity = inventory.returnQuantity(itemName);
            output.setText("Item: " + itemName + " , New Quantity: " + updatedQuantity + " updated in inventory");
        }

    }

}
