package com.metrosoftwaresolutions.inventory_application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

public class Modify_Items_Controller {

    private Stack<Product> command_list = new Stack();
    private Stack<String> command_type = new Stack();

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField tfProduct;

    @FXML
    private TextField tfQuantity;

    @FXML
    private TextField confirmation;

    private boolean isValidUserInput() {
        boolean quantityIsNumber = true;
        try { Integer.parseInt(tfQuantity.getText()); }
        catch (NumberFormatException e) { quantityIsNumber = false; }
        if (tfProduct.getText().isEmpty()) {
            confirmation.setText("Please enter a product name.");
            return false;
        }
        else if (tfQuantity.getText().isEmpty()) {
            confirmation.setText("Please enter the quantity.");
            return false;
        }
        else if (!quantityIsNumber || Integer.parseInt(tfQuantity.getText()) < 0 ) {
            confirmation.setText("Invalid quantity.");
            return false;
        }
        else
            return true;
    }

    @FXML
    private void add_item_button(ActionEvent event) {
        if (!isValidUserInput())
            return;
        String product_name = tfProduct.getText().toLowerCase();
        int quantity = Integer.parseInt(tfQuantity.getText());
        command_type.push("add");
        Product product = new Product(product_name, quantity);
        product.setCommand(new Add_Product_Command(product));
        product.executeCommand();
        command_list.push(product);
        confirmation.setText(quantity + " " + product_name + " added to inventory.");
    }

    @FXML
    private void remove_item_button(ActionEvent event) {
        if (!isValidUserInput())
            return;
        String product_name = tfProduct.getText().toLowerCase();
        int quantity = Integer.parseInt(tfQuantity.getText());
        Inventory inventory = Inventory.getInstance();
        Integer quantity_in_stock = inventory.getQuantity(product_name);
        if (quantity_in_stock == -1)
            confirmation.setText(product_name.substring(0,1).toUpperCase()+product_name.substring(1)+" does not exist in inventory.");
        else if (quantity > quantity_in_stock)
            confirmation.setText("Removing too many, there are " + quantity_in_stock + " in stock.");
        else {
            command_type.push("remove");
            Product product = new Product(product_name, quantity);
            product.setCommand(new Remove_Product_Command(product));
            product.executeCommand();
            command_list.push(product);
            confirmation.setText(quantity + " " + product_name + " removed from inventory.");
        }
    }

    @FXML
    private void undo_button(ActionEvent event) {
        if (!command_list.isEmpty()) {
            Product p = command_list.pop();
            if (command_type.pop() == "add") {
                p.undoCommand();
                confirmation.setText("Undone: \"" + p.getQuantity() + " " + p.getName() + " added to inventory.\"");
            } else {
                p.undoCommand();
                confirmation.setText("Undone: \"" + p.getQuantity() + " " + p.getName() + " removed from inventory.\"");
            }
        }
        else {
            confirmation.setText("Nothing to undo.");
        }
    }

    @FXML
    private void back_btn(ActionEvent event) throws IOException {
        command_list.clear();
        command_type.clear();
        root = FXMLLoader.load(getClass().getResource("Home_Screen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}