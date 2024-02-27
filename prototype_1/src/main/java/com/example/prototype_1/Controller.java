package com.example.prototype_1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    Inventory inventory = Inventory.getInstance();

    @FXML
    private TextField output;

    @FXML
    private TextField tfProductName;

    @FXML
    void btnSearch(ActionEvent event) {

        Stage mainWindow = (Stage) tfProductName.getScene().getWindow();

        Product item = new Product(null,-1);
        item.setName(tfProductName.getText().toLowerCase()); //accept user input and make case-insensitive
        item.setQuantity(inventory.returnQuantity(item.getName()));

        if (item.getQuantity() == -1)   //if product doesn't exist in inventory, output "(Product) does not exist in inventory."
            output.setText(item.getName().substring(0,1).toUpperCase()+item.getName().substring(1)+" does not exist in inventory.");
        else                            //else output "There are # (product) in stock."
            output.setText("There are "+item.getQuantity()+" "+item.getName()+" in stock.");
    }
}
