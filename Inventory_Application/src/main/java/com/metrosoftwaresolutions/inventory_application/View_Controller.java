package com.metrosoftwaresolutions.inventory_application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import java.io.IOException;

public class View_Controller {

    private final String PASSWORD = "password";

    private Stage stage;
    private Scene scene;
    private Parent root;

    //back button for all views
    @FXML
    public void back_btn(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Home_Screen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //home screen controls here
    @FXML
    private TextField password_field;

    @FXML
    public void btn_switch_to_search_scene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Search_View.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void btn_switch_to_show_all_scene(ActionEvent event)  throws IOException {

    }
    @FXML
    public void btn_switch_to_add_item_scene(ActionEvent event) throws IOException {
        Stage mainWindow = (Stage) password_field.getScene().getWindow();
        if (password_field.getText().isEmpty() || !password_field.getText().equals(PASSWORD)) {
            password_field.clear();
            password_field.setPromptText("Please enter password.");
            return;
        }
        else {
            root = FXMLLoader.load(getClass().getResource("Add_Items_View.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    //search inventory controls here
    @FXML
    private TextField output;

    @FXML
    private TextField tfProductName;

    @FXML
    void btnSearch(ActionEvent event) {

        Stage mainWindow = (Stage) tfProductName.getScene().getWindow();
        if (tfProductName.getText().isEmpty()) {
            output.setText("Please enter a product name.");
            return;
        }
        Inventory inventory = Inventory.getInstance();
        Product item = new Product(null,-1);
        item.setName(tfProductName.getText().toLowerCase()); //accept user input and make case-insensitive
        item.setQuantity(inventory.returnQuantity(item.getName()));

        if (item.getQuantity() == -1)   //if product doesn't exist in inventory, output "(Product) does not exist in inventory."
            output.setText(item.getName().substring(0,1).toUpperCase()+item.getName().substring(1)+" does not exist in inventory.");
        else                            //else output "There are # (product) in stock."
            output.setText("There are "+item.getQuantity()+" "+item.getName()+" in stock.");
    }

    //show all inventory controls here

    //add item to inventory controls here
    @FXML
    private TextField AddProductName;

    @FXML
    private TextField AddQty;

    @FXML
    private TextField confirmation;

    @FXML
    void add_item_button(ActionEvent event) {
        String itemName = AddProductName.getText().toLowerCase();
        int quantity = Integer.parseInt(AddQty.getText());

        Inventory inventory = Inventory.getInstance();
        if (inventory.returnQuantity(itemName) == -1){
            inventory.addItem(itemName, quantity);
            confirmation.setText("Item: " + itemName + ", Quantity: " + quantity + " added to inventory.");
        } else { // If the item already exists, update its quantity
            inventory.updateQuantity(itemName,quantity);
            int updatedQuantity = inventory.returnQuantity(itemName);
            confirmation.setText("Item: " + itemName + " , New Quantity: " + updatedQuantity + " updated in inventory");
        }

    }
}

