package com.metrosoftwaresolutions.inventory_application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main_Application extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main_Application.class.getResource("Home_Screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setTitle("Inventory Management");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        Inventory inventory = Inventory.getInstance();

        // Load existing inventory data from JSON file
        inventory.loadInventory();

        // Initialize inPurchaseHandler
        PurchaseHandler purchaseHandler = new PurchaseHandler();

        // fill into the purchase queue with Products
        for (Product product : inventory.getAllInventory()) {
            purchaseHandler.addToQueue(product);
        }
        
        //start threads here
        new Thread(new PurchaseHandler()).start();
        

        //launch the GUI
        launch();

        // Call saveInventory() method to save the updated inventory data
        Inventory.getInstance().saveInventory();
    }
}
