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
import java.util.ArrayDeque;
import java.util.Deque;

public class Modify_Items_Controller {

    public static Deque<Product> command_list = new ArrayDeque<Product>();

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

    public void executeCommands() {
        while (!command_list.isEmpty()) {
            Product p = command_list.remove();
            p.executeCommand();
        }
    }

    @FXML
    private void add_item_button(ActionEvent event) {
        //checking for valid input first
        if (!isValidUserInput())
            return;
        String name = tfProduct.getText().toLowerCase();
        int quantity = Integer.parseInt(tfQuantity.getText());
        Product product = new Product(name, quantity);
        product.setCommand( new Add_Product_Command(product));
        command_list.add(product);
        confirmation.setText(quantity + " " + name + " added to inventory.");
    }

    @FXML
    private void remove_item_button(ActionEvent event) {
//        if (!isValidUserInput())
//            return;
//        String name = tfProduct.getText().toLowerCase();
//        int quantity = Integer.parseInt(tfQuantity.getText());
//        Product product = new Product(name, quantity);
//        product.setCommand( new Remove_Product_Command(product));
//        command_list.add(product);
//        confirmation.setText(quantity + " " + name + " removed from inventory.");
        confirmation.setText("10 items removed from inventory.");
    }

    @FXML
    private void undo_button(ActionEvent event) {
        if (!command_list.isEmpty()) {
            Product p = command_list.removeLast();
            confirmation.setText("Undone: \"" + p.getQuantity() + " " + p.getName() + " added to inventory.\"");
        }
        else {
            confirmation.setText("Nothing to undo.");
        }
    }

    @FXML
    private void back_btn(ActionEvent event) throws IOException {
        //clicking the back button applies all inventory change commands
        executeCommands();
        root = FXMLLoader.load(getClass().getResource("Home_Screen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}